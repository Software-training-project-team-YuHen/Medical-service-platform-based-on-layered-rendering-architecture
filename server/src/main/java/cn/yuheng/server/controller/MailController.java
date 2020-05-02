package cn.yuheng.server.controller;

import cn.yuheng.server.model.User;
import cn.yuheng.server.service.MailService;
import cn.yuheng.server.service.UserService;
import cn.yuheng.server.util.Result;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/10 下午8:58
 */
@Controller
public class MailController {

    @Autowired
    @Setter
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("getCheckCode")
    public Result getCheckCode(HttpSession session, String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        session.setAttribute("checkCode", checkCode);
        session.setAttribute("email", email);
        session.setAttribute("email-send-time", new Date());
        String message = "您的注册验证码为：" + checkCode + "\n验证码10分钟失效。";
        mailService.sendSimpleMail(email, "注册验证码", message);
        return Result.success();
    }
}
