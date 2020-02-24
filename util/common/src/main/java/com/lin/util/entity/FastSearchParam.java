package com.lin.util.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 快速查询对象
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
public class FastSearchParam implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6226612278159326109L;

    /**
     * 查询的字段名
     */
    private String fieldName;

    /**
     * 是否是子查询
     */
    private boolean subQuery = false;

    /**
     * 值
     */
    private List<String> ids;

    public FastSearchParam(String fieldName) {
        this.fieldName = fieldName;
    }

    public FastSearchParam(String fieldName, List<String> ids) {
        this.fieldName = fieldName;
        this.ids = ids;
        this.subQuery = true;
    }
}
