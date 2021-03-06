package org.william.racekart.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class NoSetterMethodException extends RuntimeException {
    private Class<?> classType;
    private Field field;
    private String methodName;

    public NoSetterMethodException(Field field, String methodName) {
        super(MessageFormat.format("No method {0} found in class {1} for field {2}", methodName, field.getDeclaringClass().getSimpleName(), field.getName()));
        setClassType(field.getDeclaringClass());
        setField(field);
        setMethodName(methodName);
    }
}
