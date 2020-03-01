package com.lin.dto;

import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

/**
 * 角色 Dto
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色")
public class RoleDTO extends BaseDTO {
    private static final long serialVersionUID = 2650271278017486331L;

    /**
     * 角色名称
     */
    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;

    /**
     * 角色组编码
     */
    @ApiModelProperty(name = "groupCode", value = "groupCode")
    private String groupCode;

    /**
     * 角色组值
     */
    @ApiModelProperty(name = "groupValue", value = "角色组值")
    private String groupValue;

    /**
     * 备注
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    /**
     * 资源编号
     */
    @ApiModelProperty(name = "resourceIds[]", value = "资源编号数组")
    private Set<String> resourceIds;

    /**
     * 资源名称
     */
    @ApiModelProperty(name = "resourceNames[]", value = "资源名称数组")
    private Set<String> resourceNames;

    /**
     * 是否启用
     */
    @ApiModelProperty(name = "enabled", value = "是否启用")
    private boolean enabled;
}
