package org.william.racekart.util;

import org.william.racekart.converter.NoSetterMethodException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    private static final String GETTER_PREFIX = "get";
    private static final String GETTER_BOOLEAN_PREFIX = "is";

    private static final String SETTER_PREFIX = "set";


    public static Method getSetterMethod(Field field) throws NoSetterMethodException {
        String setterName = getSetterMethodName(field);
        try {
            return field.getDeclaringClass().getMethod(setterName, field.getType());
        } catch (NoSuchMethodException e) {
            throw new NoSetterMethodException(field, setterName);
        }

    }

    private static String getSetterMethodName(Field field) {
        return SETTER_PREFIX + StringUtil.toFirstLetterUpperCase(field.getName());
    }
}
