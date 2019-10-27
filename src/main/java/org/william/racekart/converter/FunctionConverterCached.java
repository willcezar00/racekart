package org.william.racekart.converter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FunctionConverterCached {
    private static final Map<Class<?>, FunctionConverter<?>> FUNCTION_CONVERTER_BY_CLASS;

    static {
        Map<Class<?>, FunctionConverter<?>> functionConverterByClass = new HashMap<>();

        functionConverterByClass.put(String.class, new StringConverter());
        for (Class<?> suportedType : NumberConverter.getSupportedTypes()) {
            try {
                functionConverterByClass.put(suportedType, new NumberConverter<>(suportedType));
            } catch (UnsupportedTypeConverterException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        FUNCTION_CONVERTER_BY_CLASS = Collections.unmodifiableMap(functionConverterByClass);
    }

    public static <TYPE> FunctionConverter<TYPE> getConverterFunction(Class<TYPE> classType) throws UnsupportedTypeConverterException {
        if (!FUNCTION_CONVERTER_BY_CLASS.containsKey(classType)) throw new UnsupportedTypeConverterException();
        return (FunctionConverter<TYPE>) FUNCTION_CONVERTER_BY_CLASS.get(classType);
    }
}
