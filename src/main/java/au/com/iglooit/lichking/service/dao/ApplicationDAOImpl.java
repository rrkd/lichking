package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.Application;
import com.google.appengine.api.datastore.Key;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:56 AM
 */
@Repository
@Transactional
public class ApplicationDAOImpl extends BaseRepository<Application> implements ApplicationDAO {

    public ApplicationDAOImpl() {
        super(Application.class);
    }

    @Override
    public Application findByUrl(String url) {
        Query q = getEntityManager().createQuery("select c from Application c where c.applicationURL=:applicationURL ")
                .setParameter("applicationURL", url);
        List<Application> result = q.getResultList();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public void createApplication(Application application) {
        add(application);
    }

    @Override
    public Application loadByUrl(String url) {
        Query q = getEntityManager().createQuery("select c from Application c " +
                "join fetch c.applicationOwnUsers ca " +
                "where c.applicationURL=:applicationURL ")
                .setParameter("applicationURL", url);
        List<Application> result = q.getResultList();
        if (result != null && result.size() > 0) {
            Application application = result.get(0);
//            application.getApplicationOwnUsers().size();
            return application;
        }
        return null;
    }

    @Override
    public Application loadByKey(Key key) {
        Application application = findByKey(key);
        application.getApplicationOwnUsers().size();
        return application;
    }
}
