package au.com.iglooit.lichking.controller;

import au.com.iglooit.lichking.exception.AppX;
import au.com.iglooit.lichking.service.PayPalService;
import com.paypal.adaptive.api.requests.PayRequest;
import com.paypal.adaptive.api.requests.PaymentDetailsRequest;
import com.paypal.adaptive.api.responses.PayResponse;
import com.paypal.adaptive.api.responses.PaymentDetailsResponse;
import com.paypal.adaptive.core.ActionType;
import com.paypal.adaptive.core.ClientDetails;
import com.paypal.adaptive.core.EndPointUrl;
import com.paypal.adaptive.core.FeesPayerType;
import com.paypal.adaptive.core.PaymentDetails;
import com.paypal.adaptive.core.PaymentExecStatus;
import com.paypal.adaptive.core.Receiver;
import com.paypal.adaptive.core.ServiceEnvironment;
import com.paypal.adaptive.exceptions.InvalidAPICredentialsException;
import com.paypal.adaptive.exceptions.InvalidResponseDataException;
import com.paypal.adaptive.exceptions.MissingAPICredentialsException;
import com.paypal.adaptive.exceptions.MissingParameterException;
import com.paypal.adaptive.exceptions.RequestFailureException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 23/10/2014
 * Time: 2:58 PM
 */
@Controller
public class PayPalController {
    @Resource
    private PayPalService payPalService;
    public static ServiceEnvironment PPEnv = ServiceEnvironment.SANDBOX;

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String pay() {
        return "pay";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ModelAndView pay(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView("pay-result");
        try {
            StringBuilder url = new StringBuilder();
            url.append(req.getRequestURL());

            String returnURL = "http://localhost:7912/paymentSuccess?payKey=${payKey}";
            String cancelURL = "http://localhost:7912/paymentCancel?userId=100";

            String ipnURL = "http://pp-pymt-reconciler.appspot.com/paymentreconciler";
            String[] receiverEmailItems = req
                    .getParameterValues("receiveremail");
            String[] amountItems = req.getParameterValues("amount");
            String[] primaryItems = req.getParameterValues("primary");
            String actionTypeStr = req.getParameter("actionType");
            ActionType actionType = ActionType.valueOf(actionTypeStr);

            PaymentDetails paymentDetails = new PaymentDetails(actionType);


            PayRequest payRequest = new PayRequest("en_US",
                    PPEnv);

            for (int i = 0; i < receiverEmailItems.length; i++) {
                String recreceiverEmail = receiverEmailItems[i];
                if (recreceiverEmail != null
                        && recreceiverEmail.length() != 0) {
                    Receiver rec1 = new Receiver();
                    rec1.setAmount((new Double(amountItems[i])
                            .doubleValue()));
                    rec1.setEmail(receiverEmailItems[i]);
                    rec1.setPrimary(Boolean.parseBoolean(primaryItems[i]));
                    paymentDetails.addToReceiverList(rec1);
                }
            }

            ClientDetails cl = new ClientDetails();
            cl.setIpAddress(req.getRemoteAddr());
            cl.setApplicationId("Praveen's GAE Sample");
            paymentDetails.setCancelUrl(cancelURL);
            paymentDetails.setReturnUrl(returnURL);
            paymentDetails.setIpnNotificationUrl(ipnURL);
            if (req.getParameter("email") != null && req.getParameter("email").length() > 0)
                paymentDetails.setSenderEmail((String) req.getParameter("email"));
            paymentDetails.setCurrencyCode((String) req
                    .getParameter("currencyCode"));
            // set feespayer
            String feesPayer = req.getParameter("feesPayer");
            if (feesPayer != null && feesPayer.length() > 0)
                paymentDetails.setFeesPayer(FeesPayerType.valueOf(feesPayer));
            payRequest.setClientDetails(cl);

            String pkey = req.getParameter("preapprovalKey");
            if (pkey != null && pkey.length() >= 20) {
                paymentDetails.setPreapprovalKey(pkey);
            }
            String memo = req.getParameter("memo");
            if (memo != null & memo.length() > 0) {
                paymentDetails.setMemo(memo);
            }

            payRequest.setPaymentDetails(paymentDetails);

//        resp.getWriter().println( payRequest.toString());

            PayResponse payResp = payRequest.execute(PayPalService.getCredentialObj());

            String payRespString = payResp.toString();

            if (payResp != null) {
                // set the authorization url if it needs to be authorized
                if (payResp.getPaymentExecStatus() != null
                        && payResp.getPaymentExecStatus() == PaymentExecStatus.CREATED) {
                    req.setAttribute("payKey", payResp.getPayKey());
                    req.setAttribute("stdUrl", EndPointUrl.getStdAuthorizationUrl(PPEnv, false));
                    req.setAttribute("epUrl", EndPointUrl.getEmbeddedAuthorizationUrl(PPEnv, null));
                    req.setAttribute("payRespStr", payResp.toString());
                    req.setAttribute("payReqStr", payRequest.toString());

                    // include the redirectHandler.jsp
                    ModelAndView modelAndView1 = new ModelAndView("/redirectHandler");
                    return modelAndView1;
                }
            }
            modelAndView.addObject("payResp", payRespString);


        } catch (IOException e) {
            resp.getWriter().println("Payment Failed w/ IOException");
        } catch (MissingAPICredentialsException e) {
            // No API Credential Object provided - log error
            e.printStackTrace();
            resp.getWriter().println("No APICredential object provided");
        } catch (InvalidAPICredentialsException e) {
            // invalid API Credentials provided - application error - log error
            e.printStackTrace();
            resp.getWriter().println("Invalid API Credentials " + e.getMissingCredentials());
        } catch (MissingParameterException e) {
            // missing parameter - log  error
            e.printStackTrace();
            resp.getWriter().println("Missing Parameter error: " + e.getParameterName());
        } catch (RequestFailureException e) {
            // HTTP Error - some connection issues ?
            e.printStackTrace();
            resp.getWriter().println("Request HTTP Error: " + e.getHTTP_RESPONSE_CODE());
        } catch (InvalidResponseDataException e) {
            // PayPal service error
            // log error
            e.printStackTrace();
            resp.getWriter().println("Invalid Response Data from PayPal: \"" + e.getResponseData() + "\"");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/paymentSuccess", method = RequestMethod.GET)
    public String paymentSuccess() {
        return "paymentSuccess";
    }

    @RequestMapping(value = "/paymentCancel", method = RequestMethod.GET)
    public String paymentCancel() {
        return "paymentCancel";
    }

    @RequestMapping(value = "/executePayment", method = RequestMethod.GET)
    public String executePayment() {
        return "executePayment";
    }

    @RequestMapping(value = "/preapproval", method = RequestMethod.GET)
    public String preapproval() {
        return "preapproval";
    }

    @RequestMapping(value = "/refund", method = RequestMethod.GET)
    public String refund() {
        return "refund";
    }

    @RequestMapping(value = "/payDetails", method = RequestMethod.GET)
    public ModelAndView payDetails(@RequestParam("payKey") String payKey) {
        if (StringUtils.isBlank(payKey)) {
            return new ModelAndView("payDetails");
        }
        try {
            PaymentDetailsRequest paymentDetailsRequest = new PaymentDetailsRequest("en_US",
                    PPEnv);

            if (payKey != null)
                paymentDetailsRequest.setPayKey(payKey);

            PaymentDetailsResponse response = paymentDetailsRequest.execute(PayPalService.getCredentialObj());

            ModelAndView modelAndView = new ModelAndView("payment-details-callback");
            modelAndView.addObject("paymentRequest", paymentDetailsRequest.toString());
            modelAndView.addObject("paymentDetails", response.toString());
            return modelAndView;
        } catch (IOException e) {
            throw new AppX("IO exception", e);
        } catch (Exception e) {
            throw new AppX("exception", e);
        }
    }

    @RequestMapping(value = "/preapprovalDetails", method = RequestMethod.GET)
    public String preapprovalDetails() {
        return "preapprovalDetails";
    }

    @RequestMapping(value = "/preapprovalDetails", method = RequestMethod.POST)
    public String preapprovalDetails(@RequestParam("preapprovalKey") String preapprovalKey) {
        throw new UnsupportedOperationException("need to implement");
    }

    @RequestMapping(value = "/cancelPreapproval", method = RequestMethod.GET)
    public String cancelPreapproval() {
        return "cancelPreapproval";
    }

    @RequestMapping(value = "/currencyConversion", method = RequestMethod.GET)
    public String currencyConversion() {
        return "currencyConversion";
    }

    @RequestMapping(value = "/setPaymentOptions", method = RequestMethod.GET)
    public String setPaymentOptions() {
        return "setPaymentOptions";
    }

    @RequestMapping(value = "/getPaymentOptions", method = RequestMethod.GET)
    public String getPaymentOptions() {
        return "getPaymentOptions";
    }

    @RequestMapping(value = "/getFundingPlans", method = RequestMethod.GET)
    public String getFundingPlans() {
        return "getFundingPlans";
    }
}
