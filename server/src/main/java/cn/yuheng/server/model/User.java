package cn.yuheng.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 下午4:27
 */

@Data
public class User {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private Object sex;

    private String email;

    private Integer headPortraitId;

    @JsonIgnore
    private String password;

    private Date registrationTime;

    private String phone;

    private Object type;

    private String status;

    @JsonIgnore
    private String wxOpenid;

    @JsonIgnore
    private String wxSession;
}