package au.com.iglooit.lichking.service.application;

import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.ApplicationOwnUser;
import au.com.iglooit.lichking.model.entity.FeeType;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 4:10 PM
 */
public interface ApplicationOwnUserManageService {
    void createOwnUser(Application application, String userId, String paypalEmail, FeeType feeType);
    ApplicationOwnUser findOwnUser(Application application, String userId);
}
