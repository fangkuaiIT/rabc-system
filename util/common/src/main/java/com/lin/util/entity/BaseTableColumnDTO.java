package com.lin.util.entity;

import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础自定义列 DTO
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@ApiModel("基础自定义列")
public class BaseTableColumnDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -327622396978079987L;

}
