package cn.yuheng.server.exception;

import lombok.Getter;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/5/2 下午5:07
 */
public class ErrorCodeException extends RuntimeException {
    @Getter
    private Integer code;

    public ErrorCodeException(Integer code) {
        this.code = code;
    }

    public ErrorCodeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ErrorCodeException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCodeException() {
    }

    public ErrorCodeException(String message) {
        super(message);
    }

    public ErrorCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorCodeException(Throwable cause) {
        super(cause);
    }

    public ErrorCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorCodeException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public ErrorCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
