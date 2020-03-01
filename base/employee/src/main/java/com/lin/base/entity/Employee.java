package com.lin.base.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 员工
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_employee")
public class Employee extends BaseEntity {
    private static final long serialVersionUID = -5256820788731834465L;

    /**
     * 名称
     */
    private String name;

    /**
     * 部门编号
     */
    private String departmentId;

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
}
