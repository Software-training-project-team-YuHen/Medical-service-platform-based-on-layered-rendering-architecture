package cn.yuheng.server.DTO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 下午5:02
 */
@Data
public class SessionDTO {
    private String openid;
    @JSONField(name = "session_key")
    private String sessionKey;
}
