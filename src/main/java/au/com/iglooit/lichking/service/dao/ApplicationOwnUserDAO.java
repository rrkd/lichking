package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.ApplicationOwnUser;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:58 AM
 */
public interface ApplicationOwnUserDAO extends IEntityService<ApplicationOwnUser> {
    void createApplicationOwnUser(ApplicationOwnUser ownUser);
    ApplicationOwnUser findByUserId(Application application, String userId);
}
