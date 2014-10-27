package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.ExpiredUser;
import au.com.iglooit.lichking.utils.DateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 4:21 PM
 */
@Repository
@Transactional
public class ExpiredUserDAOImpl extends BaseRepository<ExpiredUser> implements ExpiredUserDAO {
    public ExpiredUserDAOImpl() {
        super(ExpiredUser.class);
    }

    @Override
    public List<ExpiredUser> checkExpiredUser() {
        Date now = DateUtils.getNow();

        Query q = getEntityManager()
                .createQuery("select user from ExpiredUser user " +
                        "where user.expiredDate <= :expiredDate ")
                .setParameter("expiredDate", DateUtils.getNow());
        return (List<ExpiredUser>)q.getResultList();
    }
}
