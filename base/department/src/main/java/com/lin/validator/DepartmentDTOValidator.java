package com.lin.validator;


import com.lin.dto.DepartmentDTO;
import com.lin.service.DepartmentService;
import com.yyfly.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * DepartmentDTO 验证器
 * @version : 1.0
 */
@Component
public class DepartmentDTOValidator implements Validator {

    @Autowired
    private DepartmentService departmentService;


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
            throw new GlobalException("部门信息不能为空!");
        }
        DepartmentDTO dto = (DepartmentDTO) object;

        if (StringUtils.isEmpty(dto.getName())){
            throw new GlobalException("部门名称不能为空!");
        }

        if (!StringUtils.isEmpty(dto.getPid())){
            if (!departmentService.exists(dto.getPid())){
                throw new GlobalException("上级部门不存在!");
            }
        }
    }
}
