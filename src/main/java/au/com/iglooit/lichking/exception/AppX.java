package au.com.iglooit.lichking.exception;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 19/10/2014
 * Time: 10:29 AM
 */
public class AppX extends RuntimeException {
    public AppX (String error) {
        super(error);
    }

    public AppX(String error, Throwable e) {
        super(error, e);
    }
}
