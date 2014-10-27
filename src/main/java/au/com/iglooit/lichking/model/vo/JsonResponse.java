package au.com.iglooit.lichking.model.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 3:38 PM
 */
public class JsonResponse implements Serializable {
    public static final String OK = "OK";
    public static final String Error = "Error";
    private String status = "";
    private String errorMessage = "";

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
