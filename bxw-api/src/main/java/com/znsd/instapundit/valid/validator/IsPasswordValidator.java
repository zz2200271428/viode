package com.znsd.instapundit.valid.validator;

import com.znsd.instapundit.valid.IsPassword;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordValidator implements ConstraintValidator<IsPassword, String> {

    @Override
    public void initialize(IsPassword isPassword) {
    }

    // 只能输入5-20个以字母开头、可带数字、“_”、“.”的字串
    //  ^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$";
        boolean isEmpty = StringUtils.isEmpty(password);
        return isEmpty == true ? false : password.matches(regex);
    }
}
