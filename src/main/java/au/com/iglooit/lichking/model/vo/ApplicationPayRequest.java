package au.com.iglooit.lichking.model.vo;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 26/10/2014
 * Time: 1:35 PM
 */
public class ApplicationPayRequest extends ApplicationRequest {
    private String userId;
    private String payEmail;
    private String planId;
    private Float money;
    private String description;
    private String callback;

    public ApplicationPayRequest() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayEmail() {
        return payEmail;
    }

    public void setPayEmail(String payEmail) {
        this.payEmail = payEmail;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    @Override
    public Boolean validation() {
        if (StringUtils.isBlank(planId)) {
            if (money == null) {
                return Boolean.FALSE;
            }
            if (StringUtils.isBlank(description)) {
                return Boolean.FALSE;
            }
            if (money < 0) {
                return Boolean.FALSE;
            }
            if (StringUtils.isBlank(callback)) {
                return Boolean.FALSE;
            }
        }
        return super.validation();
    }
}
