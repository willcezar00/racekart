package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class NoConstructorConverterException extends Exception {
    private Class<?> converterClass;
    private Field field;

    public NoConstructorConverterException(Class<?> converterClass, Field field) {
        super(MessageFormat.format("No empty constructor available in converter class {0} of  field {1} in class {2}.", converterClass.getSimpleName(), field.getName(), field.getDeclaringClass().getSimpleName()));
        setField(field);
    }
}
