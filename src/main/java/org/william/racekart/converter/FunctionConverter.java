package org.william.racekart.converter;

public interface FunctionConverter<TYPE> {
    TYPE convert(String columnValue) throws Exception;
}
