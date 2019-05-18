package com.meritkapital.trade_validator.validation;

import com.meritkapital.trade_validator.validation.validator.CurrencyPairValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CurrencyPairValidator.class})
public @interface CurrencyPairValidation {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "";
}
