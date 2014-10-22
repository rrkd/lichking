package au.com.iglooit.lichking.controller;

import au.com.iglooit.lichking.exception.AppX;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 19/10/2014
 * Time: 9:59 AM
 */
@Controller
public class AuthCallbackController {
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @ResponseBody
    public String checkAuth(@RequestParam("msg_signature") String msgSignature,
                            @RequestParam("timestamp") String timeStamp,
                            @RequestParam("nonce") String nonce,
                            @RequestParam("echostr") String echostr,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sToken = "Or6lYa7jCssxuSpwBUgAjTJeeEdBJ";
        String sCorpID = "wx5ec3f09ea17ce019";
        String sEncodingAESKey = "lcRV5YGznnVps7cXtIIKRssuyiWhfySCZTgXhzMRekD";

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        String sVerifyMsgSig = URLDecoder.decode(msgSignature, "UTF-8");
        String sVerifyTimeStamp = URLDecoder.decode(timeStamp, "UTF-8");
        String sVerifyNonce = URLDecoder.decode(nonce, "UTF-8");
        String sVerifyEchoStr = URLDecoder.decode(echostr, "UTF-8");
        String sEchoStr; //需要返回的明文
        try {
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
                    sVerifyNonce, sVerifyEchoStr);
            System.out.println("verifyurl echostr: " + sEchoStr);
            // 验证URL成功，将sEchoStr返回
            return sEchoStr;
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            throw new AppX("Can't check the Auth", e);
        }
    }
}
