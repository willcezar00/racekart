package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class NotNullException extends RuntimeException  {
    private Field field;

    public NotNullException(Field field) {
        super(MessageFormat.format("The field {0} is not nullable.", field.getName()));
        setField(field);
    }
}
