package au.com.iglooit.lichking.service;

import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.PaymentTransaction;
import au.com.iglooit.lichking.model.entity.PaymentTransactionType;
import au.com.iglooit.lichking.model.vo.ApplicationPayRequest;
import au.com.iglooit.lichking.properties.WebProperties;
import au.com.iglooit.lichking.service.dao.ApplicationDAO;
import au.com.iglooit.lichking.service.dao.PaymentTransactionDAO;
import com.google.appengine.api.datastore.KeyFactory;
import com.paypal.adaptive.api.requests.PayRequest;
import com.paypal.adaptive.core.ActionType;
import com.paypal.adaptive.core.ClientDetails;
import com.paypal.adaptive.core.FeesPayerType;
import com.paypal.adaptive.core.PaymentDetails;
import com.paypal.adaptive.core.Receiver;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 26/10/2014
 * Time: 4:59 PM
 */
@Service
public class PaymentDetailHelper {
    @Resource
    private PayPalService payPalService;
    @Resource
    private ApplicationDAO applicationDAO;
    @Resource
    private PaymentTransactionDAO paymentTransactionDAO;

    public PayRequest generatePaymentDetails(ApplicationPayRequest request, HttpServletRequest req) {
        Application application = applicationDAO.findByUrl(request.getApplicationId());

        // start a new payment transaction
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setPaymentTransactionType(PaymentTransactionType.START);
        transaction.setBuyerEmail(request.getPayEmail());
        transaction.setSellerEmail(application.getPaypalEmail());
        paymentTransactionDAO.createPaymentTransaction(transaction);
        WebProperties webProperties = WebProperties.getInstance();

        String hostName = webProperties.get("host.name");
        String returnURL = hostName + "/app/paymentSuccess?payKey=${payKey}&transactionId="
                + transaction.getKeyString() + "&callback=" + request.getCallback();
        String cancelURL = hostName + "/app/paymentCancel?transactionId=" + request.getUserId();

        String ipnURL = hostName + "/paymentreconciler";

        ActionType actionType = ActionType.PAY;

        PaymentDetails paymentDetails = new PaymentDetails(actionType);


        PayRequest payRequest = new PayRequest("en_US", PayPalService.PPEnv);
        Receiver rec1 = new Receiver();
        rec1.setAmount(new Double(request.getMoney()));
        rec1.setEmail(application.getPaypalEmail());
        rec1.setPrimary(false);
        paymentDetails.addToReceiverList(rec1);

        ClientDetails cl = new ClientDetails();
        cl.setIpAddress(req.getRemoteAddr());
        cl.setApplicationId("WatchDog Application");
        paymentDetails.setCancelUrl(cancelURL);
        paymentDetails.setReturnUrl(returnURL);
        paymentDetails.setIpnNotificationUrl(ipnURL);

        paymentDetails.setSenderEmail(request.getPayEmail());

        paymentDetails.setCurrencyCode("AUD");
        // set feespayer
        paymentDetails.setFeesPayer(FeesPayerType.SENDER);

        payRequest.setClientDetails(cl);

        String memo = request.getDescription();
        if (memo != null & memo.length() > 0) {
            paymentDetails.setMemo(memo);
        }
        payRequest.setPaymentDetails(paymentDetails);
        return payRequest;
    }
}
