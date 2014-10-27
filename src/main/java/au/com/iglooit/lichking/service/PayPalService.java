package au.com.iglooit.lichking.service;

import au.com.iglooit.lichking.exception.AppX;
import au.com.iglooit.lichking.model.paypal.AdaptiveRequests;
import au.com.iglooit.lichking.properties.WebProperties;
import com.paypal.adaptive.api.requests.PayPalBaseRequest;
import com.paypal.adaptive.core.APICredential;
import com.paypal.adaptive.core.ServiceEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 23/10/2014
 * Time: 2:12 PM
 */
@Component
public class PayPalService {
    private static final Logger log = Logger
            .getLogger(PayPalService.class.getName());
    public static ServiceEnvironment PPEnv = ServiceEnvironment.SANDBOX;
    private static APICredential credentialObj;
    @PostConstruct
    public void init() {
        WebProperties webProperties = WebProperties.getInstance();

        // Get the value of APIUsername
        String APIUsername = webProperties.get("paypal.API.Username");
        String APIPassword = webProperties.get("paypal.API.Password");
        String APISignature = webProperties.get("paypal.API.Signature");
        String AppID = webProperties.get("paypal.AppID");
        String AccountEmail = webProperties.get("paypal.AccountEmail");
        String PPEnvironment = webProperties.get("paypal.Environment");

        String HTTP_CONNECTION_TIMEOUT = webProperties.get("paypal.HTTP.CONNECTION.TIMEOUT");
        String HTTP_READ_TIMEOUT = webProperties.get("paypal.HTTP.READ.TIMEOUT");
        String DISABLE_SSL_CERT_CHECK = webProperties.get("paypal.DISABLE.SSL.CERT.CHECK");




        if (APIUsername == null || APIUsername.length() <= 0
                || APIPassword == null || APIPassword.length() <= 0
                || APISignature == null || APISignature.length() <= 0
                || AppID == null || AppID.length() <= 0) {
            // requires API Credentials not set - throw exception
            throw new AppX("APICredential(s) missing");
        }

        credentialObj = new APICredential();
        credentialObj.setAPIUsername(APIUsername);
        credentialObj.setAPIPassword(APIPassword);
        credentialObj.setSignature(APISignature);
        credentialObj.setAppId(AppID);
        credentialObj.setAccountEmail(AccountEmail);
        // set the HTTP connection configs
        PayPalBaseRequest.DISABLE_SSL_CERT_CHECK = Boolean.parseBoolean(DISABLE_SSL_CERT_CHECK);
        PayPalBaseRequest.HTTP_CONNECTION_TIMEOUT = Integer.parseInt(HTTP_CONNECTION_TIMEOUT);
        PayPalBaseRequest.HTTP_READ_TIMEOUT = Integer.parseInt(HTTP_READ_TIMEOUT);

        if (PPEnvironment != null) {
            if (PPEnvironment.equals("BETA_SANDBOX")) {
                AdaptiveRequests.PPEnv = ServiceEnvironment.BETA_SANDBOX;
            } else if (PPEnvironment.equals("PRODUCTION")) {
                AdaptiveRequests.PPEnv = ServiceEnvironment.PRODUCTION;
            } else if (PPEnvironment.equals("SANDBOX")) {
                AdaptiveRequests.PPEnv = ServiceEnvironment.SANDBOX;
            } else if (PPEnvironment.equals("STAGING")) {
                AdaptiveRequests.PPEnv = ServiceEnvironment.STAGING;
            } else {
                AdaptiveRequests.PPEnv = ServiceEnvironment.SANDBOX;
            }
        }
        log.info("Servlet initialized successfully");
    }

    public static APICredential getCredentialObj() {
        return credentialObj;
    }
}
