package org.william.racekart.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogCustomConverter {
    Class<? extends FunctionConverter<?>> customConverter() default DefaultFunctionConverter.class;
}

class DefaultFunctionConverter implements FunctionConverter<Object> {
    @Override
    public Object convert(String arg) {
        return arg;
    }
}
