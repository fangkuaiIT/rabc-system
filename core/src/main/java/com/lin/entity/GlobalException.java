package com.lin.entity;



import com.lin.constant.HttpStatus;
import lombok.Getter;

/**
 * 通用全局异常
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
@Getter
public class GlobalException extends RuntimeException {

    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;


    /**
     * Global exception.
     *
     * @param message the message
     */
    public GlobalException(String message) {
        super(message);
        this.code = HttpStatus.CONDITION_ERROR.value();
        this.message = message;
    }

    /**
     * Global exception.
     *
     * @param httpStatus the http status
     */
    public GlobalException(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }
}
