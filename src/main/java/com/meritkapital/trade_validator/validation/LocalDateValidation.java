package com.meritkapital.trade_validator.validation;

import com.meritkapital.trade_validator.model.TradeStyle;
import com.meritkapital.trade_validator.util.DateComparisionType;
import com.meritkapital.trade_validator.validation.validator.LocalDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LocalDateValidator.class})
@Repeatable(LocalDateValidation.List.class)
public @interface LocalDateValidation {

    //Base field
    String baseField();

    //Field with which base field will be compared
    String fieldToCompare();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "";

    TradeStyle tradeStyle() default TradeStyle.ANY;

    //Comparision type which defining how dates should be validated
    DateComparisionType dateComparisionType();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    @interface List {
        LocalDateValidation[] value();
    }

}
