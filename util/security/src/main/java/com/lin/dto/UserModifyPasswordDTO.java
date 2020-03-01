package com.lin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户修改密码DTO
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
public class UserModifyPasswordDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -845687146757161511L;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    /**
     * 旧密码
     */
    @ApiModelProperty(name = "originPassword", value = "旧密码")
    private String originPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(name = "newPassword", value = "新密码")
    private String newPassword;

    /**
     * 重复新密码
     */
    @ApiModelProperty(name = "repeat", value = "重复新密码")
    private String repeat;
}
