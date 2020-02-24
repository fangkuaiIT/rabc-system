package com.lin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3149058137580929651L;

    /**
     * 唯一编号
     */
    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 最后一次更新人
     */
    private String updateBy;

    /**
     * 最后一次更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 删除标志
     */
    private Integer deleteTag;
}
