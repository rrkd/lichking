package au.com.iglooit.lichking.model.vo;

import au.com.iglooit.lichking.model.entity.FeeType;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 4:26 PM
 */
public class ApplicationCreateUserRequest extends ApplicationRequest {
    private String userId;
    private String feeType;
    private String paypalEmail;
    public ApplicationCreateUserRequest() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }
}
