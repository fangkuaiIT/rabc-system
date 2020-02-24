package com.lin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA 基础 DTO
 * @author : fangkauiIT / fangkauiIT
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JpaBaseDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8747977691780390133L;

    /**
     * 版本
     */
    private Long version;
}
