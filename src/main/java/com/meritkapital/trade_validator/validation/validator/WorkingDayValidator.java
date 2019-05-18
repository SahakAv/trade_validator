package com.meritkapital.trade_validator.validation.validator;

import com.meritkapital.trade_validator.model.request.TradeRequest;
import com.meritkapital.trade_validator.util.Utils;
import com.meritkapital.trade_validator.util.ValidationMessageBuilder;
import com.meritkapital.trade_validator.validation.WorkingDayValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//Validating if the passed Local date is a not weekend or a public holiday
public class WorkingDayValidator implements ConstraintValidator<WorkingDayValidation, TradeRequest> {

    private String fieldName;

    private static final List<DayOfWeek> NON_WORKING_DAYS = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    //Taking for example cyprus public holiday, can be replaced with usage of some API like CALENDARIFIC
    private static final List<LocalDate> PUBLIC_HOLIDAYS = Arrays.asList(LocalDate.of(2019, 1, 1),
            LocalDate.of(2019, 1, 6),
            LocalDate.of(2019, 3, 11),
            LocalDate.of(2019, 3, 25),
            LocalDate.of(2019, 4, 29),
            LocalDate.of(2019, 4, 20),
            LocalDate.of(2019, 12, 26));

    @Override
    public void initialize(WorkingDayValidation constraintAnnotation) {
        this.fieldName = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(TradeRequest value, ConstraintValidatorContext context) {
        LocalDate fieldValue = null;
        try {
            fieldValue = Utils.getFieldValue(value, fieldName, LocalDate.class);
            if (NON_WORKING_DAYS.contains(fieldValue.getDayOfWeek())){
                return false;
            }
             return !PUBLIC_HOLIDAYS.contains(fieldValue);
        } catch (Exception e) {
            context.buildConstraintViolationWithTemplate(ValidationMessageBuilder.buildErrorMessage(fieldName, fieldValue)).addConstraintViolation();
            return false;
        }
    }
}
