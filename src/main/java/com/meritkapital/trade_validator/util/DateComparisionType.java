package com.meritkapital.trade_validator.util;

import java.time.LocalDate;
import java.util.function.BiPredicate;

public enum DateComparisionType {
    DATE_IS_BEFORE(LocalDate::isBefore),
    DATE_IS_AFTER(LocalDate::isAfter);

    private BiPredicate<LocalDate, LocalDate> validator;

    DateComparisionType(BiPredicate<LocalDate, LocalDate> validator) {
        this.validator = validator;
    }

    public BiPredicate<LocalDate, LocalDate> getValidator() {
        return validator;
    }
}
