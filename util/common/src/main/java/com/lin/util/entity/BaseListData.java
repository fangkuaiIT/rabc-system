package com.lin.util.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * BaseListData
 * @param <T> the type parameter
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@ApiModel("分页数据")
abstract class BaseListData<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6020322859354075134L;

    /**
     * 当前页码
     */
    @ApiModelProperty(name = "number", value = "当前页码")
    private int number;

    /**
     * 页码size
     */
    @ApiModelProperty(name = "size", value = "页码size")
    private int size;

    /**
     * 总页数
     */
    @ApiModelProperty(name = "totalPages", value = "总页数")
    private int totalPages;

    /**
     * 总记录数
     */
    @ApiModelProperty(name = "totalElements", value = "总记录数")
    private long totalElements;

    /**
     * 是否有上一页
     */
    @ApiModelProperty(name = "hasPrevious", value = "是否有上一页")
    private boolean hasPrevious;

    /**
     * 是否有下一页
     */
    @ApiModelProperty(name = "hasNext", value = "是否有下一页")
    private boolean hasNext;

    /**
     * 是否第一页
     */
    @ApiModelProperty(name = "isFirst", value = "是否第一页")
    private boolean isFirst;

    /**
     * 是否最后一页
     */
    @ApiModelProperty(name = "isLast", value = "是否最后一页")
    private boolean isLast;

    /**
     * 原始data
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private List<T> originData;

    /**
     * 获取当前页码
     *
     * @return the int
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract int getNumber();

    /**
     * 获取当前页码大小
     *
     * @return the int
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract int getSize();

    /**
     * 获取总页数
     *
     * @return the int
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract int getTotalPages();

    /**
     * 获取总记录数
     *
     * @return the long
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract long getTotalElements();

    /**
     * 获取是否有上一页
     *
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract boolean getHasPrevious();

    /**
     * 获取是否有下一页
     *
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract boolean getHasNext();

    /**
     * 获取是否是第一页
     *
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract boolean getIsFirst();

    /**
     * 获取是否是最后一页
     *
     * @return the boolean
     * @author : fangkuaiIt / 2019-01-07
     */
    public abstract boolean getIsLast();

    /**
     * 获取原始数据
     *
     * @return the list
     * @author : fangkuaiIt / 2019-01-08
     */
    public abstract List<T> getOriginData();
}
