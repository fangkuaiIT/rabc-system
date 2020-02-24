package com.lin.search;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 基础查询对象
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
@Data
public abstract class BaseSearchCriteria implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6686103841929106216L;


    /**
     * 默认开启分页
     */
    private final static Boolean DEFAULT_PAGING = true;

    /**
     * 默认页数
     */
    private final static long DEFAULT_PAGE = 1;

    /**
     * 默认显示数量
     */
    private final static long DEFAULT_SIZE = 10;

    /**
     * 是否开启分页
     */
    private Boolean paging = DEFAULT_PAGING;

    /**
     * 页数
     */
    private long page = DEFAULT_PAGE;

    /**
     * 页码显示数量
     */
    private long size = DEFAULT_SIZE;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序方向
     */
    private Order order;

    /**
     * Search params
     */
    private Set<SearchParam> searchParams;


    /**
     * 排序枚举
     *
     * @author : fangkauiIT
     * @version : 1.0
     */
    public enum Order {
        /**
         * 升序.
         */
        asc,
        /**
         * 降序.
         */
        desc
    }
}
