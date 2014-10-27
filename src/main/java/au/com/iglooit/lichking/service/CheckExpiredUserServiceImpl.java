package au.com.iglooit.lichking.service;

import au.com.iglooit.lichking.model.entity.ExpiredUser;
import au.com.iglooit.lichking.model.entity.UserState;
import au.com.iglooit.lichking.service.dao.ExpiredUserDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 8:58 PM
 */
@Service
public class CheckExpiredUserServiceImpl implements CheckExpiredUserService {
    @Resource
    private ExpiredUserDAO expiredUserDAO;

    public void checkExpiredUser(){
        List<ExpiredUser> expiredUserList = expiredUserDAO.checkExpiredUser();
        if (expiredUserList.size() > 0) {
            for(ExpiredUser user : expiredUserList) {
                user.setUserState(UserState.Expired);
                expiredUserDAO.update(user);
            }
        }
    }
}
