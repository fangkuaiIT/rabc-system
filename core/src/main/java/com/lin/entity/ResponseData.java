package com.lin.entity;



import com.lin.constant.HttpStatus;
import lombok.Getter;

import java.io.Serializable;

/**
 * 响应数据格式
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
@Getter
public class ResponseData implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1839421797104392633L;

    /**
     * 状态码
     */
    public Integer code;

    /**
     * 消息
     */
    public String message;

    /**
     * 数据
     */
    public Object data;


    /**
     * Response data.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     */
    public ResponseData(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Success response data.
     *
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData success() {
        return success("request succeeded");
    }

    /**
     * Success response data.
     *
     * @param data the data
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData success(Object data) {
        return success("request succeeded", data);
    }

    /**
     * Success response data.
     *
     * @param message the message
     * @param data    the data
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData success(String message, Object data) {
        return success(HttpStatus.OK.value(), message, data);
    }

    /**
     * Success response data.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData success(Integer code, String message, Object data) {
        return build(code, message, data);
    }

    /**
     * Error response data.
     *
     * @param code the code
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData error(Integer code) {
        return error(code, "request fail");
    }

    /**
     * Error response data.
     *
     * @param code    the code
     * @param message the message
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData error(Integer code, String message) {
        return error(code, message, null);
    }

    /**
     * Error response data.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData error(Integer code, String message, Object data) {
        return build(code, message, data);
    }

    /**
     * Build response data.
     *
     * @param status the status
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData build(HttpStatus status) {
        return build(status, null);
    }

    /**
     * Build response data.
     *
     * @param status the status
     * @param data   the data
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData build(HttpStatus status, Object data) {
        return build(status.value(), status.getReasonPhrase(), data);
    }

    /**
     * Build response data.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     * @return the response data
     * @author : yangjunqing / 2019-04-13
     */
    public static ResponseData build(Integer code, String message, Object data) {
        return new ResponseData(code, message, data);
    }
}
