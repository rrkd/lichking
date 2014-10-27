package au.com.iglooit.lichking.model.vo;

import au.com.iglooit.lichking.model.entity.ApplicationOwnUser;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:41 AM
 */
public class ApplicationCheckUserResponse extends JsonResponse {
    private String userId;
    private String paymentStatus;
    private Date startDate;
    private Date expireDate;

    public ApplicationCheckUserResponse() {
        super(JsonResponse.OK, "");
    }

    public ApplicationCheckUserResponse(ApplicationOwnUser ownUser) {
        this();
        this.userId = ownUser.getUserId();
        this.expireDate = ownUser.getExpiredDate();
        this.paymentStatus = ownUser.getUserState().name();
        this.startDate = ownUser.getStartDate();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
