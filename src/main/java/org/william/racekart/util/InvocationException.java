package org.william.racekart.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class InvocationException extends RuntimeException {
    private Method method;
    private Exception cause;

    public InvocationException(Method method, Exception cause) {
        super(MessageFormat.format("The method {0} of class {1}, throws the exception {2}. ", method.getName(), method.getDeclaringClass().getSimpleName(), cause.getMessage()));
        setMethod(method);
        setCause(cause);
    }
}
