package com.lin.dto;


import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 部门 DTO
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)

public class DepartmentDTO  extends BaseDTO {
    private static final long serialVersionUID = 6973208280261796691L;

    /**
     * 父id
     */
    @ApiModelProperty(name = "pid", value = "上一级部门编号")
    private String pid;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(name = "note", value = "备注")
    private String note;

    /**
     * 是否启用
     */
    @ApiModelProperty(name = "enabled", value = "是否启用")
    private boolean enabled;
}
