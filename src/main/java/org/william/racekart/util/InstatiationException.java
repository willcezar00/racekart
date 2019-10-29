package org.william.racekart.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class InstatiationException extends RuntimeException {
    private Class<?> clazz;
    private Exception cause;

    public InstatiationException(Class<?> clazz, Exception cause) {
        super(MessageFormat.format("The class {0} throws the exception {1} ", clazz.getName(), cause.getMessage()), cause);
        setClazz(clazz);
        setCause(cause);
    }
}

