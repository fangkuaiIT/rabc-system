package com.lin.dto;




import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/**
 * 用户 Dto
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户")
public class UserDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7744793387748956224L;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(name = "password", value = "密码", hidden = true)
    private String password;

    /**
     * 最后一次密码设置时间
     */
    @ApiModelProperty(name = "lastPasswordReset", value = "最后一次密码设置时间", hidden = true)
    private Date lastPasswordReset;

    /**
     * 确认密码
     */
    @ApiModelProperty(name = "repeat", value = "确认密码", hidden = true)
    private String repeat;

    /**
     * token
     */
    @ApiModelProperty(name = "token", value = "token(登录返回字段)")
    private String token;

    /**
     * 员工编号
     */
    @ApiModelProperty(name = "employeeId", value = "员工编号")
    private String employeeId;

    /**
     * 员工名称
     */
    @ApiModelProperty(name = "employeeName", value = "员工名称")
    private String employeeName;

    /**
     * 角色编号
     */
    @ApiModelProperty(name = "roleIds[]", value = "角色编号数组")
    private Set<String> roleIds;

    /**
     * 角色名称
     */
    @ApiModelProperty(name = "roleNames[]", value = "角色名称数组")
    private Set<String> roleNames;

    /**
     * 角色组
     */
    @ApiModelProperty(name = "roleGroupCodes[]", value = "角色组数组")
    private Set<String> roleGroupCodes;

    /**
     * 权限
     */
    @ApiModelProperty(name = "authorityDTO", value = "权限")
    private Set<AuthorityDTO> authorityDTOS;

    /**
     * 是否启用
     */
    @ApiModelProperty(name = "enabled", value = "是否启用")
    private boolean enabled;
}
