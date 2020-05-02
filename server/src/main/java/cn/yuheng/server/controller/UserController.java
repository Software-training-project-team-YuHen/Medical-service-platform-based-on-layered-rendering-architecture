package cn.yuheng.server.controller;

import cn.yuheng.server.model.User;
import cn.yuheng.server.service.LoginService;
import cn.yuheng.server.service.UserService;
import cn.yuheng.server.util.PasswordUtil;
import cn.yuheng.server.util.Result;
import com.google.code.kaptcha.Constants;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/5 下午7:18
 */
@RestController
public class UserController {

    @Autowired
    @Setter
    private UserService userService;

    @Autowired
    @Setter
    private LoginService loginService;


    @GetMapping(value = "/api/user/remove")
    public Result<User> removeUser(Integer id) {
        userService.removeUser(id);
        return Result.success();
    }

    @GetMapping(value = "/api/user/get")
    public Result<User> getUser(Integer id) {
        User user = userService.getByID(id);
        return Result.successOrFail(user);
    }

    @GetMapping(value = "/api/user/get/by-email")
    public Result<User> getUserByEmail(String email) {
        User user = userService.getByEmail(email);
        return Result.successOrFail(user);
    }

    @PostMapping("/api/user/set-password/by-email")
    public Result setPassword(HttpSession session, @RequestBody Map<String, String> userJson) {
        String email = userJson.get("email");
        String password = userJson.get("password");
        String checkCode = userJson.get("checkCode");
        Date mailSendTinme = (Date) session.getAttribute("email-send-time");
        Date now = new Date();
        if (password == null || email == null || checkCode == null) {
            return Result.fail("参数错误");
        }
        String rightCheckCode = (String) session.getAttribute("checkCode");
        if (rightCheckCode == null) {
            return Result.fail("请获取邮箱验证码");
        }
        if (now.getTime() - mailSendTinme.getTime() > 600000) {
            return Result.fail("邮箱验证码已过期");
        }
        if (!checkCode.equals(rightCheckCode)) {
            return Result.fail("邮箱验证码错误");
        }
        if (!email.equals(session.getAttribute("email"))) {
            return Result.fail("邮箱错误");
        }
        User user = userService.getByEmail(email);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        User userClone = new User();
        userClone.setId(user.getId());
        userClone.setPassword(password);
        userService.updateSelective(userClone);
        return Result.success();
    }

    @PostMapping(value = "/api/user/create/by-email")
    public Result createUser(HttpSession session, @RequestBody Map<String, String> userJson) {
        String email = userJson.get("email");
        String password = userJson.get("password");
        String captcha = userJson.get("captcha");
        if (email == null || password == null) {
            return Result.fail("参数错误");
        }
        if (captcha != session.getAttribute(Constants.KAPTCHA_SESSION_KEY)) {
            return Result.fail("验证码错误");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        if (null != userService.getByEmail(email)) {
            return Result.fail("邮箱账号已被注册");
        }
        return Result.successOrFail(userService.addUser(user));
    }

    @PostMapping(value = "/api/user/login/by-email")
    public Result<User> login(HttpSession session, HttpServletRequest request, HttpServletResponse response, String email, String password, Long time, Boolean rememberMe) {
        if (password.isEmpty()) {
            Cookie[] cookies = request.getCookies();
            email = null;
            password = null;
            time = null;
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "userEmail":
                        email = cookie.getValue();
                        break;
                    case "password":
                        password = cookie.getValue();
                        break;
                    case "userLoginTimestamp":
                        time = Long.parseLong(cookie.getValue());
                }
            }
            if (email == null || password == null || time == null) {
                return Result.fail();
            }
            User user = userService.getByEmail(email);
            if (user == null) {
                return Result.fail();
            }
            if (PasswordUtil.checkPassword(password, time, user.getPassword(), Long.MAX_VALUE)) {
                login(session, user);
                user.setPassword(null);
                return Result.success(user);
            } else {
                return Result.fail();
            }
        } else {

            User user = userService.getByEmail(email);
            if (user == null) {
                return Result.fail();
            }
            if (PasswordUtil.checkPassword(password, time, user.getPassword())) {
                if (rememberMe) {
                    Cookie userEmail = new Cookie("userEmail", user.getEmail());
                    userEmail.setPath("/api/user/login/by-email");
                    Cookie userPassword = new Cookie("password", password);
                    userPassword.setPath("/api/user/login/by-email");
                    Cookie userLoginTimestamp = new Cookie("userLoginTimestamp", String.valueOf(time));
                    userLoginTimestamp.setPath("/api/user/login/by-email");
                    response.addCookie(userEmail);
                    response.addCookie(userPassword);
                    response.addCookie(userLoginTimestamp);
                }
                login(session, user);
                user.setPassword(null);
                return Result.success(user);
            } else {
                return Result.fail();
            }
        }
    }

    public void login(HttpSession session, User user) {
        session.setAttribute("user", user);
        loginService.addLoginHistory(user.getId());
    }

    @PostMapping(value = "/api/user/logout")
    public Result logout(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return Result.fail("未登录");
        }
        session.removeAttribute("user");
        return Result.success();
    }
}
