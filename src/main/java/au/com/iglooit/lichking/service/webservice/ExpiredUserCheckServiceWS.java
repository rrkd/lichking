package au.com.iglooit.lichking.service.webservice;

import au.com.iglooit.lichking.service.CheckExpiredUserService;
import au.com.iglooit.lichking.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 3:34 PM
 */
@Controller
public class ExpiredUserCheckServiceWS {
    @Resource
    private CheckExpiredUserService checkExpiredUserService;

    @RequestMapping(value = "/ws/checkExpiredUser", method = RequestMethod.GET)
    @ResponseBody
    public String help() {
        Date now = DateUtils.getNow();
        checkExpiredUserService.checkExpiredUser();
        return "This is weixin test server";
    }
}
