package org.william.racekart.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Invocationxception extends RuntimeException {
    private Method method;
    private Exception exception;

    public Invocationxception(Method method, Exception exception) {
        super(MessageFormat.format("The method {0} of class {1}, throws the exception {2}. ", method.getName(), method.getDeclaringClass().getSimpleName(), exception.getMessage()));
        setMethod(method);
        setException(exception);
    }
}
