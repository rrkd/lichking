package au.com.iglooit.lichking.model.vo;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:52 AM
 */
public class ApplicationCheckUserRequest extends ApplicationRequest {
    private String userId;
    public ApplicationCheckUserRequest() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
