package com.lin.config.properties;

import lombok.Data;

/**
 * Jpa 属性配置
 * @author : fangkauiIT
 * @version : 1.0
 */
@Data
public class SeerJpaProperties {

    /**
     * 是否开启逻辑删除
     */
    private Boolean enableLogicDelete = false;
}
