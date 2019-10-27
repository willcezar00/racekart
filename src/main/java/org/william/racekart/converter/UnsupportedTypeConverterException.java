package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class UnsupportedTypeConverterException extends Exception {
    private Field field;

    public UnsupportedTypeConverterException() {
    }

    public UnsupportedTypeConverterException(Field field) {
        super(MessageFormat.format("Unsupported type {0} of  field {1} in class {2}.", field.getType().getName(), field.getName(), field.getDeclaringClass().getSimpleName()));
        setField(field);
    }


}
