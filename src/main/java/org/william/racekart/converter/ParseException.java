package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class ParseException extends RuntimeException {
    private Field field;
    private String value;

    public ParseException(Field field, String value) {
        super(MessageFormat.format("Could not parse {0} to  field {1} of class {2}.", value, field.getName(), field.getDeclaringClass().getSimpleName()));
        setField(field);
        setValue(value);
    }
}
