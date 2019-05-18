package com.meritkapital.trade_validator.validation.validator;

import com.meritkapital.trade_validator.model.TradeStyle;
import com.meritkapital.trade_validator.model.request.TradeRequest;
import com.meritkapital.trade_validator.util.Utils;
import com.meritkapital.trade_validator.util.ValidationMessageBuilder;
import com.meritkapital.trade_validator.validation.LocalDateValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.function.BiPredicate;

//Validating  Local Dates by a passed DateComparisionType type
public class LocalDateValidator implements ConstraintValidator<LocalDateValidation, TradeRequest> {

    private String baseField;

    private String fieldToCompare;

    private BiPredicate<LocalDate, LocalDate> validator;

    private TradeStyle tradeStyle;

    @Override
    public void initialize(LocalDateValidation constraintAnnotation) {
        this.baseField = constraintAnnotation.baseField();
        this.fieldToCompare = constraintAnnotation.fieldToCompare();
        this.validator = constraintAnnotation.dateComparisionType().getValidator();
        this.tradeStyle = constraintAnnotation.tradeStyle();
    }


    @Override
    public boolean isValid(TradeRequest tradeRequest, ConstraintValidatorContext constraintValidatorContext) {
        //Some validation are needed only for specific trade style
        boolean needToIgnoreValidation = tradeStyle != TradeStyle.ANY && tradeStyle != tradeRequest.getStyle();
        if (needToIgnoreValidation) {
            return true;
        }
        LocalDate baseFieldValue = null;
        LocalDate fieldToCompareValue = null;
        try {
            baseFieldValue = Utils.getFieldValue(tradeRequest, this.baseField, LocalDate.class);
            fieldToCompareValue = Utils.getFieldValue(tradeRequest, this.fieldToCompare, LocalDate.class);
            return validator.test(baseFieldValue, fieldToCompareValue);
        } catch (Exception e) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessageBuilder.buildErrorMessage(baseField, baseFieldValue)).addConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessageBuilder.buildErrorMessage(fieldToCompare, fieldToCompareValue)).addConstraintViolation();
            //Returning true as error message already added
            return false;
        }
    }
}
