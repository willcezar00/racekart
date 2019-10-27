package org.william.racekart.converter;

import org.william.racekart.util.ArrayUtil;
import org.william.racekart.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EntityConverter<TYPE> implements Converter<TYPE> {

    private Map<Integer, Field> fieldPosition = new HashMap<>();
    private Map<Field, String> headerByField = new HashMap<>();
    private Map<Field, Method> setterMethodByField = new HashMap<>();
    private Map<Field, FunctionConverter<?>> converterByField = new HashMap<>();
    private Set<Field> nullableFields = new HashSet<>();

    @Override
    public TYPE convert(String[] row) {
        return null;
    }

    public EntityConverter(String[] headers, Class<TYPE> typeClass) throws NoSetterMethodException, NoConstructorConverterException {
        Map<String, Integer> indexByHeader = ArrayUtil.mapIndexByElement(headers);
        for (Field field : typeClass.getDeclaredFields()) {
            LogColumn logColumn = typeClass.getAnnotation(LogColumn.class);
            String header = logColumn == null ? field.getName() : logColumn.name();
            if (!indexByHeader.containsKey(header)) continue;
            if (logColumn == null || logColumn.nullable()) {
                nullableFields.add(field);
            }
            if (typeClass.isAnnotationPresent(LogCustomConverter.class)
                    && !typeClass.getAnnotation(LogCustomConverter.class).customConverter().equals(DefaultFunctionConverter.class)) {
                try {
                    FunctionConverter<?> functionConverter = typeClass.getAnnotation(LogCustomConverter.class).customConverter().getConstructor().newInstance();
                    converterByField.put(field, functionConverter);
                } catch (Exception e) {
                    throw new NoConstructorConverterException(typeClass.getAnnotation(LogCustomConverter.class).customConverter(), field);
                }
            }
            setterMethodByField.put(field, ReflectionUtil.getSetterMethod(field));

            Method setterMethod = ReflectionUtil.getSetterMethod(field);
        }
    }


}
