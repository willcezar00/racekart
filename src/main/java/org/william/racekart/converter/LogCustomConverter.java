package org.william.racekart.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogCustomConverter {
    Class<? extends Function<String, ?>> customConverter() default DefaultFunction.class;
}

class DefaultFunction implements Function<String, Object> {
    @Override
    public Object apply(String o) {
        return null;
    }
}
