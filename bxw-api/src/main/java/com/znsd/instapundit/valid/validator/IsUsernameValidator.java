package com.znsd.instapundit.valid.validator;

import com.znsd.instapundit.valid.IsUsername;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//IsUsername：自定义的注解
//String：注解参数类型
public class IsUsernameValidator implements ConstraintValidator<IsUsername, String> {

    @Override
    public void initialize(IsUsername isUsername) {
    }

    // 用户名（4到16位，字母数字下划线，减号）
    // /^[-_a-zA-Z0-9]{4,16}$/
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^[-_a-zA-Z0-9]{4,16}$";
        boolean isEmpty = StringUtils.isEmpty(username);
        return isEmpty == true ? false : username.matches(regex);
    }
}
