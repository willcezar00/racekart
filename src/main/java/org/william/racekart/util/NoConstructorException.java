package org.william.racekart.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class NoConstructorException extends RuntimeException {
    private Class<?> clazz;

    public NoConstructorException(Class<?> clazz) {
        super(MessageFormat.format("No empty constructor available in converter class {0}.", clazz.getName()));
        setClazz(clazz);
    }
}
