package au.com.iglooit.lichking.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 2:19 PM
 */
@Entity
public class ApplicationOwnUser extends UnSearchEntity {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;
    private FeeType feeType;
    private Date startDate;
    private Date expiredDate;
    private UserState userState;
    private String paypalEmail;

    public ApplicationOwnUser() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }
}
