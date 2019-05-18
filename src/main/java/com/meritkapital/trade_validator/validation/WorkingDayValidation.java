package com.meritkapital.trade_validator.validation;

import com.meritkapital.trade_validator.validation.validator.WorkingDayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {WorkingDayValidator.class})
public @interface WorkingDayValidation {

    String field();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "";

}
