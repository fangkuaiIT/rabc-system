package com.lin.util.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询对象
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
@ApiModel("查询对象")
public class SearchParam implements Serializable {
    private static final long serialVersionUID = 8435192367957361622L;

    /**
     * 查询的字段名
     */
    @ApiModelProperty(name = "fieldName", value = "查询的字段名")
    private String fieldName;

    /**
     * 查询方式
     */
    @ApiModelProperty(name = "operator", value = "查询方式(eq-等于, ne-不等于, like-模糊查询, between-区间(日期查询专用), ge-大于等于, le-小于等于, gt-大于, lt-小于, in-范围(值为以','连接的字符串， 例如 id1,id2...))")
    private String operator;

    /**
     * 值1
     */
    @ApiModelProperty(name = "value", value = "值1")
    private Object value;

    /**
     * 值2(日期查询需要用到值1和值2)
     */
    @ApiModelProperty(name = "value1", value = "值2(日期查询需要用到值1和值2)")
    private Object value1;
}
