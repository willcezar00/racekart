package org.william.racekart.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    private static final String GETTER_PREFIX = "get";
    private static final String GETTER_BOOLEAN_PREFIX = "is";

    private static final String SETTER_PREFIX = "set";


    public static Method getSetterMethod(Field field) {
        String setterName = getSetterMethodName(field);
        try {
            return field.getDeclaringClass().getMethod(setterName, field.getType());
        } catch (NoSuchMethodException e) {
            throw new NoSetterMethodException(field, setterName);
        }

    }

    public static <TYPE> TYPE newInstance(Constructor<TYPE> constructor) {
        try {
            return constructor.newInstance();
        } catch (Exception e) {
            throw new InstatiationException(constructor.getDeclaringClass(), e);
        }
    }

    public static Object invoke(Method method, Object object, Object... args) {
        try {
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new Invocationxception(method, e);
        }
    }

    private static String getSetterMethodName(Field field) {
        return SETTER_PREFIX + StringUtil.toFirstLetterUpperCase(field.getName());
    }

    public static <TYPE> Constructor<TYPE> getContructor(Class<TYPE> clazz, Class<?>... argsType) {
        try {
            return clazz.getConstructor(argsType);
        } catch (NoSuchMethodException e) {
            throw new NoConstructorException(clazz);
        }
    }

}
