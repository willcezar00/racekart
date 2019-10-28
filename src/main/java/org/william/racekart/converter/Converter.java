package org.william.racekart.converter;

import java.lang.reflect.InvocationTargetException;

public interface Converter<TYPE> {
    TYPE convert(String[] args);
}
