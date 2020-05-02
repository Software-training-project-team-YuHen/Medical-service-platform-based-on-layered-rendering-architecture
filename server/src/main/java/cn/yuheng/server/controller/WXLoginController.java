package cn.yuheng.server.controller;

import cn.yuheng.server.DTO.SessionDTO;
import cn.yuheng.server.exception.ErrorCodeException;
import cn.yuheng.server.model.User;
import cn.yuheng.server.service.UserService;
import cn.yuheng.server.util.Result;
import com.alibaba.fastjson.JSON;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 下午4:36
 */
@RestController
public class WXLoginController {
    @Autowired
    private UserService userService;

    @Setter
    private String APPID;
    @Setter
    private String SECRET;

    private static String urlTemplate = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @PostMapping(value = "/api/user/creat/by-wechat")
    public Result<String> creatByWeChat(String code) {
        try {
            SessionDTO sessionDTO = jscode2session(code);
            String wx_session = UUID.randomUUID().toString();
            String wx_openid = sessionDTO.getOpenid();

            User user = new User();
            user.setWxSession(wx_session);
            user.setWxOpenid(wx_openid);

            userService.addUser(user);

            return Result.success(wx_session);
        } catch (ErrorCodeException e) {
            return Result.fail(e.toString());
        } catch (Exception e) {
            return Result.fail(e.toString());
        }
    }

    public SessionDTO jscode2session(String code) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("content-type", "application/json")
                .url(String.format(urlTemplate, APPID, SECRET, code))
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            if (execute.isSuccessful()) {
                SessionDTO sessionDTO = JSON.parseObject(execute.body().string(), SessionDTO.class);
                return sessionDTO;
            } else {
                throw new ErrorCodeException();
            }

        } catch (IOException e) {
            throw new ErrorCodeException();
        }
    }
}
