package cn.yuheng.server.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * login_history
 *
 * @author
 */
@Data
public class LoginHistory implements Serializable {
    private Integer user;

    private Date time;

    private static final long serialVersionUID = 1L;
}