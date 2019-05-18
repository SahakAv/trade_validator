package com.meritkapital.trade_validator.util;

import java.lang.reflect.Field;

public class Utils {


    public static <T> T getFieldValue(Object object, String fieldName, Class<T> type) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object o = field.get(object);
        return type.cast(o);
    }

    public static void setFieldValue(Object object, String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, fieldValue);
    }
}
