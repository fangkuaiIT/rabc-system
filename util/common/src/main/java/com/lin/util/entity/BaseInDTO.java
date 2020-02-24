package com.lin.util.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.Set;

/**
 * 基础入参 DTO
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
@ApiIgnore("入参")
public class BaseInDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7012888506346981445L;

    /**
     * Ids
     */
    @ApiModelProperty(name = "ids", value = "编号数组")
    private Set<String> ids;

    /**
     * Enabled
     */
    @ApiModelProperty(name = "enabled", value = "是否启用(true or false)")
    private Boolean enabled;
}
