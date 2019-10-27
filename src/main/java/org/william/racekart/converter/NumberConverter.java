package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.william.racekart.util.StringUtil;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
public class NumberConverter<TYPE> implements FunctionConverter<TYPE> {
    private static final Map<Class<?>, Constructor<?>> NUMBER_CONSTRUCTOR_STRING_BY_CLASS;

    static {
        Map<Class<?>, Constructor<?>> numberConstructorStringByClass = new HashMap<>();
        List<Class<?>> primitiveClassesWrapper = Arrays.asList(Byte.class, Boolean.class, Double.class, Float.class, Integer.class, Long.class, Short.class);
        try {
            for (Class<?> classType : primitiveClassesWrapper) {
                Constructor<?> constructor = classType.getConstructor(String.class);
                Class<?> primitiveType = (Class<?>) classType.getField("TYPE").get(null);
                numberConstructorStringByClass.put(classType, constructor);
                numberConstructorStringByClass.put(primitiveType, constructor);
            }
            List<Class<?>> othersNumberClasses = Arrays.asList(BigDecimal.class, BigInteger.class);
            for (Class<?> classType : othersNumberClasses) {
                numberConstructorStringByClass.put(classType, classType.getConstructor(String.class));
            }
            NUMBER_CONSTRUCTOR_STRING_BY_CLASS = Collections.unmodifiableMap(numberConstructorStringByClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Collection<Class<?>> getSupportedTypes() {
        return NUMBER_CONSTRUCTOR_STRING_BY_CLASS.keySet();
    }

    private Constructor<TYPE> constructor;

    public NumberConverter(Class<TYPE> classType) throws UnsupportedTypeConverterException {
        if (!NUMBER_CONSTRUCTOR_STRING_BY_CLASS.containsKey(classType)) throw new UnsupportedTypeConverterException();
        setConstructor((Constructor<TYPE>) NUMBER_CONSTRUCTOR_STRING_BY_CLASS.get(classType));
    }

    @Override
    public TYPE convert(String columnValue) throws Exception {
        return StringUtil.isNullOrEmpty(columnValue) ? null : getConstructor().newInstance(columnValue);
    }

}
