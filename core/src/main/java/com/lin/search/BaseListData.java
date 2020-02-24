package com.lin.search;

import java.io.Serializable;

/**
 * 基础分页数据
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
public abstract class BaseListData implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9099608935894015636L;

    /**
     * 当前页码
     */
    private long page;

    /**
     * 当前页码显示数量
     */
    private long size;

    /**
     * 总页码
     */
    private long totalPages;

    /**
     * 总记录数
     */
    private long totalElements;

    /**
     * 是否有上一页
     */
    private Boolean hasPrevious;

    /**
     * 是否有下一页
     */
    private Boolean hasNext;

    /**
     * 是否第一页
     */
    private Boolean first;

    /**
     * 是否最后一页
     */
    private Boolean last;

    /**
     * Data
     */
    private Object data;


    /**
     * 获取当前页码.
     *
     * @return the long
     * @author : yangjunqing / 2019-04-13
     */
    public abstract long getPage();

    /**
     * 获取当前页码大小.
     *
     * @return the long
     * @author : yangjunqing / 2019-04-13
     */
    public abstract long getSize();

    /**
     * 获取总页数.
     *
     * @return the long
     * @author : yangjunqing / 2019-04-13
     */
    public abstract long getTotalPages();

    /**
     * 获取总记录数.
     *
     * @return the long
     * @author : yangjunqing / 2019-04-13
     */
    public abstract long getTotalElements();

    /**
     * 是否有上一页.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-13
     */
    public abstract Boolean getHasPrevious();

    /**
     * 是否有下一页.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-13
     */
    public abstract Boolean getHasNext();

    /**
     * 是否是第一页.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-13
     */
    public abstract Boolean isFirst();

    /**
     * 是否是最后一页.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-13
     */
    public abstract Boolean isLast();

    /**
     * 获取数据.
     *
     * @return the object
     * @author : yangjunqing / 2019-04-13
     */
    public Object getData(){
        return this.data;
    }

    /**
     * 设置数据.
     *
     * @param data the data
     * @author : yangjunqing / 2019-04-15
     */
    public void setData(Object data){
        this.data = data;
    }
}
