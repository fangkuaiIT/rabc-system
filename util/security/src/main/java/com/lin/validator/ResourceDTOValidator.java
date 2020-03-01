package com.lin.validator;



import com.lin.service.ResourceService;
import com.yyfly.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * ResourceDto 验证器
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Component
public class ResourceDTOValidator implements Validator {

    @Autowired
    private ResourceService resourceService;


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
            throw new GlobalException("资源信息不能为空!");
        }
    }
}
