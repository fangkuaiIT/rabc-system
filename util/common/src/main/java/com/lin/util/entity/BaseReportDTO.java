package com.lin.util.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
public class BaseReportDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5679538322084182699L;

    /**
     * 当前行号
     */
    @ApiModelProperty(name = "number", value = "当前行号(默认第一页，分页查询有效)")
    private Long number = 1L;

    /**
     * 每页多少条记录
     */
    @ApiModelProperty(name = "size", value = "每页多少条记录(默认10条，分页查询有效)")
    private Long size = 10L;

    /**
     * 升序字段数组
     */
    @ApiModelProperty(name = "ascs", value = "升序字段数组(分页查询有效)")
    private List<String> ascs;

    /**
     * 降序字段数组
     */
    @ApiModelProperty(name = "descs", value = "降序字段数组(分页查询有效)")
    private List<String> descs;

    /**
     * 获取分页对象.
     *
     * @param <E> the type parameter
     * @return the page
     * @author : fangkuaiIt / 2019-02-27
     */
    public <E> Page<E> of(){
        Page<E> page = new Page<>(number, size);
        page.setAscs(ascs);
        page.setDescs(descs);
        return page;
    }
}
