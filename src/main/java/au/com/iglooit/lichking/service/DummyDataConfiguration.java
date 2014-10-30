package au.com.iglooit.lichking.service;

import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.ApplicationType;
import au.com.iglooit.lichking.properties.WebProperties;
import au.com.iglooit.lichking.service.dao.ApplicationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 4:39 PM
 */
@Component
public class DummyDataConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(DummyDataConfiguration.class);
    private static final String TEST_APPLICATION_URL = "www.test.com";
    @Resource
    private ApplicationDAO applicationDAO;
    @PostConstruct
    public void init() throws Exception {
        WebProperties webProperties = WebProperties.getInstance();
        Boolean initData = Boolean.valueOf(webProperties.get("init.dummy.data"));
        if (initData) {
            LOG.info("init dummy data.");
            initDummyConfig();
        }
    }

    private void initDummyConfig() {
        generateHuafuTestData();
        generateLocalTestDate();
    }

    private void generateLocalTestDate() {
        Application application = applicationDAO.findByUrl(TEST_APPLICATION_URL);
        if(application == null) {
            application = new Application();
            application.setApplicationName("test");
            application.setApplicationType(ApplicationType.Register);
            application.setApplicationURL(TEST_APPLICATION_URL);
            application.setToken("testtoken");
            application.setUserCount(0L);
            applicationDAO.createApplication(application);
        }
        else {
            application.setPaypalEmail("zumaexhaust-facilitator@gmail.com");
            applicationDAO.update(application);
        }
    }

    private void generateHuafuTestData() {
        Application application = applicationDAO.findByUrl("spring-forest-538.appspot.com");
        if(application == null) {
            application = new Application();
            application.setApplicationName("test");
            application.setApplicationType(ApplicationType.Register);
            application.setApplicationURL("spring-forest-538.appspot.com");
            application.setToken("testtoken");
            application.setUserCount(0L);
            application.setPaypalEmail("zumaexhaust-facilitator@gmail.com");
            applicationDAO.createApplication(application);
        }
        else {
//            application.setPaypalEmail("zumaexhaust-facilitator@gmail.com");
//            applicationDAO.update(application);
        }
    }
}
