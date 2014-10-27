package au.com.iglooit.lichking.service.webservice;

import au.com.iglooit.lichking.exception.AppX;
import au.com.iglooit.lichking.model.entity.PaymentTransaction;
import au.com.iglooit.lichking.model.entity.PaymentTransactionType;
import au.com.iglooit.lichking.model.vo.JsonResponse;
import au.com.iglooit.lichking.service.PayPalService;
import au.com.iglooit.lichking.service.dao.PaymentTransactionDAO;
import com.google.appengine.api.datastore.KeyFactory;
import com.paypal.adaptive.api.requests.PaymentDetailsRequest;
import com.paypal.adaptive.api.responses.PaymentDetailsResponse;
import com.paypal.adaptive.core.PayError;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 27/10/2014
 * Time: 8:24 AM
 */
@Controller
public class PaymentServiceWS {
    @Resource
    private PaymentTransactionDAO paymentTransactionDAO;

    @RequestMapping(value = "/ws/paymentDetails", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse help(@RequestParam("payKey") String payKey,
                             @RequestParam("transactionId") String transactionId) {
        try {
            PaymentTransaction paymentTransaction = paymentTransactionDAO.findByKey(
                    KeyFactory.stringToKey(transactionId));
            if (paymentTransaction == null) {
                return new JsonResponse(JsonResponse.Error, "Transaction does not exist");
            }
            PaymentDetailsRequest paymentDetailsRequest = new PaymentDetailsRequest("en_US",
                    PayPalService.PPEnv);

            if (StringUtils.isBlank(payKey)) {
                return new JsonResponse(JsonResponse.Error, "Invalid pay key");
            }

            paymentDetailsRequest.setPayKey(payKey);

            PaymentDetailsResponse response = paymentDetailsRequest.execute(PayPalService.getCredentialObj());
            // saving the transaction

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            paymentTransaction.setPaymentDetailsJSON(ow.writeValueAsString(response.getPaymentDetails()));
            paymentTransaction.setAck(response.getResponseEnvelope().getAck().name());
            paymentTransaction.setBuild(response.getResponseEnvelope().getBuild());
            paymentTransaction.setTimestamp(response.getResponseEnvelope().getTimestamp());
            paymentTransaction.setCorrelationId(response.getResponseEnvelope().getCorrelationId());
            for (PayError payError : response.getPayErrorList()) {
                paymentTransaction.getErrorList().add(payError.getError().toString());
            }
            paymentTransaction.setPaymentTransactionType(PaymentTransactionType.SUCCESS);
            paymentTransactionDAO.update(paymentTransaction);
            return new JsonResponse(JsonResponse.OK, "");
        } catch (IOException e) {
            throw new AppX("IO exception", e);
        } catch (Exception e) {
            throw new AppX("exception", e);
        }
    }
}
