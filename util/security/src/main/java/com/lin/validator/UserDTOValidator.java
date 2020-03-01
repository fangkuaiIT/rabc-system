package com.lin.validator;




import com.lin.dto.UserDTO;
import com.lin.util.utils.RegexUtils;
import com.yyfly.common.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * UserDto 验证器
 * @author : lin
 * @version : 1.0
 */
@Component
public class UserDTOValidator implements Validator {

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
            throw new GlobalException("用户信息不能为空!");
        }
        UserDTO dto = (UserDTO) object;

        if (StringUtils.isEmpty(dto.getUsername())){
            throw new GlobalException("用户名不能为空!");
        }else {
            if (!RegexUtils.checkUserName(dto.getUsername())){
                throw new GlobalException("用户名必须符合4-16位汉字,母数,字母的组合!");
            }
        }

        if (StringUtils.isEmpty(dto.getId())){
            if (StringUtils.isEmpty(dto.getPassword())){
                throw new GlobalException("密码不能为空!");
            }else {
                if (!RegexUtils.checkPassword(dto.getPassword())){
                    throw new GlobalException("密码必须符合6-16位字母数字的组合!");
                }
            }

            if (StringUtils.isEmpty(dto.getRepeat())){
                throw new GlobalException("请再一次输入密码!");
            }
            if (!dto.getPassword().equals(dto.getRepeat())){
                throw new GlobalException("两次输入的密码不相同!");
            }
        }
    }
}
