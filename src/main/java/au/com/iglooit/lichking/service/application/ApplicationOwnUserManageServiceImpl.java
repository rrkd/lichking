package au.com.iglooit.lichking.service.application;

import au.com.iglooit.lichking.exception.AppX;
import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.ApplicationOwnUser;
import au.com.iglooit.lichking.model.entity.FeeType;
import au.com.iglooit.lichking.service.dao.ApplicationDAO;
import au.com.iglooit.lichking.service.dao.ApplicationOwnUserDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 4:10 PM
 */
@Service
public class ApplicationOwnUserManageServiceImpl implements ApplicationOwnUserManageService {
    @Resource
    private ApplicationOwnUserDAO applicationOwnUserDAO;
    @Resource
    private ApplicationDAO applicationDAO;

    @Override
    public void createOwnUser(Application application, String userId, String paypalEmail, FeeType feeType) {
        if (application.allowableAddUser()) {
            ApplicationOwnUser ownUser = new ApplicationOwnUser();
            ownUser.setFeeType(feeType);
            ownUser.setUserId(userId);
            ownUser.setPaypalEmail(paypalEmail);
            ownUser.setApplication(application);
            applicationOwnUserDAO.createApplicationOwnUser(ownUser);
            application.setUserCount(application.getUserCount() + 1);
            applicationDAO.update(application);
        } else {
            throw new AppX("Can't add a new user since you have limited");
        }
    }

    @Override
    public ApplicationOwnUser findOwnUser(Application application, String userId) {
        return applicationOwnUserDAO.findByUserId(application, userId);
    }
}
