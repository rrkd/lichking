package au.com.iglooit.lichking.service.webservice;

import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.FeeType;
import au.com.iglooit.lichking.model.vo.ApplicationCreateUserRequest;
import au.com.iglooit.lichking.model.vo.JsonResponse;
import au.com.iglooit.lichking.service.application.ApplicationOwnUserManageService;
import au.com.iglooit.lichking.service.dao.ApplicationDAO;
import com.google.appengine.api.datastore.KeyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 4:28 PM
 */
@Controller
public class ApplicationManageServiceWS {
    @Resource
    private ApplicationDAO applicationDAO;
    @Resource
    private ApplicationOwnUserManageService applicationOwnUserManageService;

    @RequestMapping(value = "/app/ownuser", method = RequestMethod.POST, headers = {"Content-Type=application/json"})
    @ResponseBody
    public JsonResponse createOwnUser(@RequestBody ApplicationCreateUserRequest request) {
        Application application = applicationDAO.findByUrl(request.getApplicationId());

        applicationOwnUserManageService.createOwnUser(application, request.getUserId(), request.getPaypalEmail(),
                FeeType.valueOf(request.getFeeType()));
        return new JsonResponse(JsonResponse.OK, "");
    }

//    @RequestMapping(value = "/app/ownuser/", method = RequestMethod.GET)
//    @ResponseBody
//    public ApplicationCreateUserRequest test() {
//        ApplicationCreateUserRequest request = new ApplicationCreateUserRequest();
//        request.setFeeType(FeeType.Annual.name());
//        request.setPaypalEmail("");
//        request.setUserId("1000");
//        request.setApplicationId("ddddd");
//        request.setApplicationToken("testtoken");
//        return request;
//    }
}
