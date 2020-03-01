package com.lin.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_department")
public class Department extends BaseEntity {
    private static final long serialVersionUID = 4869529214012385637L;

    /**
     * 父id
     */
    private String pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String note;
}
