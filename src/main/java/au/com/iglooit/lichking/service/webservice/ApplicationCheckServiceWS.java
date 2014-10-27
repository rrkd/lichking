package au.com.iglooit.lichking.service.webservice;

import au.com.iglooit.lichking.model.entity.Application;
import au.com.iglooit.lichking.model.entity.ApplicationOwnUser;
import au.com.iglooit.lichking.model.vo.ApplicationCheckUserRequest;
import au.com.iglooit.lichking.model.vo.ApplicationCheckUserResponse;
import au.com.iglooit.lichking.model.vo.JsonResponse;
import au.com.iglooit.lichking.service.application.ApplicationOwnUserManageService;
import au.com.iglooit.lichking.service.dao.ApplicationDAO;
import com.google.appengine.api.datastore.KeyFactory;
import org.apache.commons.lang.StringUtils;
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
 * Time: 10:40 AM
 */
@Controller
public class ApplicationCheckServiceWS {
    @Resource
    private ApplicationOwnUserManageService applicationOwnUserManageService;
    @Resource
    private ApplicationDAO applicationDAO;

    @RequestMapping(value = "/app/check", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse help(@RequestBody ApplicationCheckUserRequest request) {
        if (StringUtils.isBlank(request.getApplicationId())) {
            return new JsonResponse(JsonResponse.Error, "Invalid Application ID");
        }
        Application application = applicationDAO.findByKey(KeyFactory.stringToKey(request.getApplicationId()));
        if (application == null) {
            return new JsonResponse(JsonResponse.Error, "Invalid Application ID");
        }
        ApplicationOwnUser ownUser = applicationOwnUserManageService.findOwnUser(application, request.getUserId());
        if (ownUser == null) {
            return new JsonResponse(JsonResponse.Error, "Invalid User ID");
        }
        return new ApplicationCheckUserResponse(ownUser);
    }
}
