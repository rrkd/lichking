package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.ExpiredUser;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 4:20 PM
 */
public interface ExpiredUserDAO extends IEntityService<ExpiredUser> {
    List<ExpiredUser> checkExpiredUser();
}
