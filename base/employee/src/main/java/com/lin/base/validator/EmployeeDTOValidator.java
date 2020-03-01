package com.lin.base.validator;
//
//import com.seerbigdata.imms.base.dto.EmployeeDTO;
//import com.seerbigdata.imms.base.service.DepartmentService;
import com.lin.base.dto.EmployeeDTO;

import com.lin.service.DepartmentService;
import com.yyfly.common.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * EmployeeDTO 验证器
 * @author : fang
 * @version : 1.0
 */
@Component
public class EmployeeDTOValidator implements Validator {

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
            throw new GlobalException("员工信息不能为空!");
        }
        EmployeeDTO dto = (EmployeeDTO) object;

        //员工名称
        if (StringUtils.isEmpty(dto.getName())){
            throw new GlobalException("员工名称不能为空!");
        }

        //部门
        if (StringUtils.isEmpty(dto.getDepartmentId())){
            throw new GlobalException("未选择部门!");
        }else {
            if (!departmentService.exists(dto.getDepartmentId())){
                throw new GlobalException("部门不存在!");
            }
        }
    }
}
