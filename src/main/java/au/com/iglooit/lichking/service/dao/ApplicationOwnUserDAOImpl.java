package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.ApplicationOwnUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:58 AM
 */
@Repository
@Transactional
public class ApplicationOwnUserDAOImpl extends BaseRepository<ApplicationOwnUser> implements ApplicationOwnUserDAO {
    public ApplicationOwnUserDAOImpl() {
        super(ApplicationOwnUser.class);
    }

    @Override
    public void createApplicationOwnUser(ApplicationOwnUser ownUser) {
        add(ownUser);
    }

    @Override
    public ApplicationOwnUser findByUserId(Application application, String userId) {
        Query q = getEntityManager().createQuery("select c from ApplicationOwnUser c " +
                "where c.userId=:userId and c.application=:application")
                .setParameter("userId", userId)
                .setParameter("application", application);
        List<ApplicationOwnUser> result = q.getResultList();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
