package au.com.iglooit.lichking.model.vo;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:50 AM
 */
public class ApplicationRequest extends JsonRequest {
    private String applicationId;
    private String applicationToken;

    public ApplicationRequest() {

    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public Boolean validation() {
        if (StringUtils.isBlank(applicationId) || StringUtils.isBlank(applicationToken)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
