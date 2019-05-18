package com.meritkapital.trade_validator.validation.validator;

import com.meritkapital.trade_validator.util.ValidationMessageBuilder;
import com.meritkapital.trade_validator.validation.CurrencyPairValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

//Validating if the passed string contains pair of valid currencies by a ISO_4217
public class CurrencyPairValidator implements ConstraintValidator<CurrencyPairValidation, String> {

    private static final Integer ISO_4217_CHARS_LENGTH = 3;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String firstCurrency = null;
        String secondCurrency = null;
        try {
            firstCurrency = value.substring(0, ISO_4217_CHARS_LENGTH);
            secondCurrency = value.substring(3);
            Currency.getInstance(firstCurrency);
            Currency.getInstance(secondCurrency);
            return true;
        } catch (Exception e) {
            context.buildConstraintViolationWithTemplate(ValidationMessageBuilder.buildErrorMessage("firstCurrency", firstCurrency)).addConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidationMessageBuilder.buildErrorMessage("secondCurrency", secondCurrency)).addConstraintViolation();
            return false;
        }

    }
}
