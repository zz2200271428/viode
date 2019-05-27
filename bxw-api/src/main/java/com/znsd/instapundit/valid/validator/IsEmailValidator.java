package com.znsd.instapundit.valid.validator;

import com.znsd.instapundit.valid.IsEmail;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEmailValidator implements ConstraintValidator<IsEmail, String> {

    @Override
    public void initialize(IsEmail isEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
        boolean isEmpty = StringUtils.isEmpty(email);
        return isEmpty == true ? false : email.matches(regex);
    }
}
