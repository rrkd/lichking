package au.com.iglooit.lichking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 27/10/2014
 * Time: 8:34 PM
 */
@Controller
public class ApplicationPayTestController {
    @RequestMapping(value = "/app/paymentTest", method = RequestMethod.GET)
    public String paymentTest() {
        return "/app/payment-test";
    }

    @RequestMapping(value = "/app/paymentTestCB", method = RequestMethod.GET)
    public String index() {
        return "/app/payment-test-cb";
    }
}
