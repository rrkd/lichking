package au.com.iglooit.lichking.service.application;

import au.com.iglooit.lichking.model.entity.Application;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 10:34 AM
 */
public interface ApplicationManageService {
    void createApplication(Application application);
    Boolean disableApplication(Application application);

}
