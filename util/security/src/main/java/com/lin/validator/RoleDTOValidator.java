package com.lin.validator;


import com.lin.dto.RoleDTO;
import com.yyfly.common.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * RoleDto 验证器
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Component
public class RoleDTOValidator implements Validator {

    /**
     * 在WebDataBinder添加验证器后，所有的对象参数都会执行这个supports方法, 通过后spring才会检测
     * controller层的方法参数是否使用@Validated/Valid，然后执行validate方法
     * 所以为了防止错误，这里支持任何类型参数
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (object == null){
            throw new GlobalException("角色信息不能为空!");
        }
        RoleDTO dto = (RoleDTO) object;

        if (StringUtils.isEmpty(dto.getRoleName())){
            throw new GlobalException("角色名称不能为空！");
        }
    }
}
