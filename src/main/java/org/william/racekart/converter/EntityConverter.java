package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.william.racekart.util.ArrayUtil;
import org.william.racekart.util.ReflectionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class EntityConverter<TYPE> implements Converter<TYPE> {

    private Map<Integer, Field> fieldByIndex = new HashMap<>();
    private Map<Field, String> headerByField = new HashMap<>();
    private Map<Field, Method> setterMethodByField = new HashMap<>();
    private Map<Field, FunctionConverter<?>> converterByField = new HashMap<>();
    private Set<Field> nullableFields = new HashSet<>();
    private Constructor<TYPE> constructor;
    private String[] headers;

    @Override
    public TYPE convert(String[] row) {
        checkInvalidRow(row);
        TYPE entity = ReflectionUtil.newInstance(getConstructor());
        for (Map.Entry<Integer, Field> entry : getFieldByIndex().entrySet()) {
            Object value = convertValue(entry.getValue(), row[entry.getKey()]);
            checkNull(entry.getValue(), value);
            ReflectionUtil.invoke(getSetterMethodByField().get(entry.getValue()), entity, value);
        }
        return entity;
    }

    public EntityConverter(String[] headers, Class<TYPE> typeClass) {
        setHeaders(headers);
        Map<String, Integer> indexByHeader = ArrayUtil.mapIndexByElement(headers);
        for (Field field : typeClass.getDeclaredFields()) {
            LogColumn logColumn = typeClass.getAnnotation(LogColumn.class);
            String header = logColumn == null ? field.getName() : logColumn.name();
            if (!indexByHeader.containsKey(header)) continue;
            if ((logColumn == null || logColumn.nullable()) && !field.getType().isPrimitive()) {
                getNullableFields().add(field);
            }
            LogCustomConverter logCustomConverter = typeClass.getAnnotation(LogCustomConverter.class);
            if (logCustomConverter != null
                    && !logCustomConverter.customConverter().equals(DefaultFunctionConverter.class)) {
                Constructor<? extends FunctionConverter> constructor = ReflectionUtil.getContructor(logCustomConverter.customConverter());
                FunctionConverter<?> functionConverter = ReflectionUtil.newInstance(constructor);
                getConverterByField().put(field, functionConverter);
            }
            getSetterMethodByField().put(field, ReflectionUtil.getSetterMethod(field));
            getFieldByIndex().put(indexByHeader.get(header), field);
            getHeaderByField().put(field, header);
            setConstructor(ReflectionUtil.getContructor(typeClass));
        }
    }

    private void checkInvalidRow(String[] row) {
        if (row.length != getHeaders().length) throw new InvalidRowFormatException(getHeaders().length, row.length);
    }

    private Object convertValue(Field field, String value) {
        try {
            return getConverterByField().get(field).convert(value);
        } catch (Exception e) {
            throw new ParseException(field, value);
        }
    }

    private void checkNull(Field field, Object value) {
        if (value == null && !getNullableFields().contains(field)) throw new NotNullException(field);
    }
}
