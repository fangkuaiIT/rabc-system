package com.lin.dto;

import com.lin.entity.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 权限 DTO
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ApiModel("权限DTO")
public class AuthorityDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -97540456654027710L;


   Resource resource;


    private Set<Resource> children;


}
