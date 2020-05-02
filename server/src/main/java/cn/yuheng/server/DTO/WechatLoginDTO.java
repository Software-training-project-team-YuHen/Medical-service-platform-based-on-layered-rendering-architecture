package cn.yuheng.server.DTO;

import lombok.Data;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 下午4:53
 */
@Data
public class WechatLoginDTO {
    private String rawData;
    private String signature;
    private String code;
}
