package com.meritkapital.trade_validator.util;

import java.lang.reflect.Member;
import java.util.stream.Stream;

public class ValidationMessageBuilder {
    
    
    public static String buildErrorMessage(final String fieldName, final Object fieldValue){
        return "Provided " + fieldName + " " + (fieldValue == null ? "is null" : "is in invalid format");
    }
}
