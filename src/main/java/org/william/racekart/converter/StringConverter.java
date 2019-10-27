package org.william.racekart.converter;

import org.william.racekart.util.StringUtil;

public class StringConverter implements FunctionConverter<String> {

    @Override
    public String convert(String columnValue) {
        return columnValue == null ? null : columnValue.trim();
    }
}
