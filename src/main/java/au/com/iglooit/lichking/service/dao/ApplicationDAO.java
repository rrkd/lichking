package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.Application;
import com.google.appengine.api.datastore.Key;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:56 AM
 */
public interface ApplicationDAO extends IEntityService<Application> {
    Application findByUrl(String url);
    void createApplication(Application application);
    Application loadByUrl(String url);
    Application loadByKey(Key key);
}
