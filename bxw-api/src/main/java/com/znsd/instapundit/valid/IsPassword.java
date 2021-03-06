package com.znsd.instapundit.valid;


import com.znsd.instapundit.valid.validator.IsPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Constraint(validatedBy = {IsPasswordValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface IsPassword {

    //如果校验不通过返回的提示信息
    String message() default "密码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
