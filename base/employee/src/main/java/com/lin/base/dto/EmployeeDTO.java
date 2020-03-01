package com.lin.base.dto;


import com.lin.util.entity.BaseTableColumnDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 员工 DTO
 * @author : fang /
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class EmployeeDTO extends BaseTableColumnDTO {
    private static final long serialVersionUID = 2697009310561532010L;

    /**
     * 名称
     */

    private String name;

    /**
     * 用户名
     */

    private String username;

    /**
     * 部门编号
     */

    private String departmentId;

    /**
     * 部门名称
     */

    private String departmentName;

    /**
     * 邮箱
     */

    private String email;

    /**
     * 电话
     */

    private String phone;

    /**
     * 备注
     */

    private String note;

    /**
     * 是否启用
     */

    private boolean enabled;
}
