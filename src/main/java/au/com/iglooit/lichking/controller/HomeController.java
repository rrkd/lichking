package au.com.iglooit.lichking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 19/10/2014
 * Time: 9:58 AM
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    @ResponseBody
    public String help() {
        return "This is weixin test server";
    }
}
