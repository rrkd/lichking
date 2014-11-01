package au.com.iglooit.lichking.controller;

import au.com.iglooit.lichking.exception.PaymentException;
import au.com.iglooit.lichking.model.vo.ApplicationPayRequest;
import au.com.iglooit.lichking.service.PayPalService;
import au.com.iglooit.lichking.service.PaymentDetailHelper;
import com.paypal.adaptive.api.requests.PayRequest;
import com.paypal.adaptive.api.responses.PayResponse;
import com.paypal.adaptive.core.EndPointUrl;
import com.paypal.adaptive.core.PaymentExecStatus;
import com.paypal.adaptive.exceptions.InvalidAPICredentialsException;
import com.paypal.adaptive.exceptions.InvalidResponseDataException;
import com.paypal.adaptive.exceptions.MissingAPICredentialsException;
import com.paypal.adaptive.exceptions.MissingParameterException;
import com.paypal.adaptive.exceptions.RequestFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 26/10/2014
 * Time: 4:19 PM
 */
@Controller
public class ApplicationPayController {
    @Resource
    private PayPalService payPalService;
    @Resource
    private PaymentDetailHelper paymentDetailHelper;

    @RequestMapping(value = "/app/pay", method = RequestMethod.POST)
    public ModelAndView pay(@ModelAttribute("request") ApplicationPayRequest request, HttpServletRequest req,
                                      HttpServletResponse resp) throws PaymentException {
        try {
            PayRequest payRequest = paymentDetailHelper.generatePaymentDetails(request, req);

            PayResponse payResp = payRequest.execute(PayPalService.getCredentialObj());

            String payRespString = payResp.toString();

            if (payResp != null) {
                // set the authorization url if it needs to be authorized
                if (payResp.getPaymentExecStatus() != null
                        && payResp.getPaymentExecStatus() == PaymentExecStatus.CREATED) {
                    StringBuilder builder = new StringBuilder(
                            EndPointUrl.getStdAuthorizationUrl(PayPalService.PPEnv, false));
                    builder.append("&paykey=").append(payResp.getPayKey());

                    return new ModelAndView("redirect:" + builder.toString());
                }
                throw new PaymentException(PaymentException.PaymentError.PAYMENT_FLOW_ERROR);
            }
            throw new PaymentException(PaymentException.PaymentError.NO_PAY_RESPONSE);


        } catch (IOException e) {
            throw new PaymentException(PaymentException.PaymentError.IO_EX, e);
        } catch (MissingAPICredentialsException e) {
            throw new PaymentException(PaymentException.PaymentError.NO_APICREDENTIAL_EX, e);
        } catch (InvalidAPICredentialsException e) {
            // invalid API Credentials provided - application error - log error
            throw new PaymentException(PaymentException.PaymentError.INVALID_API_CREDENTIALS_EX, e);
        } catch (MissingParameterException e) {
            // missing parameter - log  error
            throw new PaymentException(PaymentException.PaymentError.MISSING_PARAMETER,
                    "Missing Parameter error: " + e.getParameterName(), e);
        } catch (RequestFailureException e) {
            // HTTP Error - some connection issues ?
            throw new PaymentException(PaymentException.PaymentError.REQUEST_HTTP_ERROR,
                    "Request HTTP Error: " + e.getHTTP_RESPONSE_CODE(), e);
        } catch (InvalidResponseDataException e) {
            // PayPal service error
            // log error
            throw new PaymentException(PaymentException.PaymentError.INVALID_RESPONSE,
                    "Invalid Response Data from PayPal: \"" + e.getResponseData() + "\"", e);
        }
    }

    @RequestMapping(value = "/app/paymentSuccess", method = RequestMethod.GET)
    public ModelAndView paySuccess(@RequestParam("payKey") String payKey,
                                   @RequestParam("transactionId") String transactionId,
                                   @RequestParam("callback") String callback) {
        ModelAndView modelAndView = new ModelAndView("app/payment-success");
        modelAndView.addObject("payKey", payKey);
        modelAndView.addObject("transactionId", transactionId);
        modelAndView.addObject("callback", callback);
        return modelAndView;
    }
}
