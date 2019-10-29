package org.william.racekart.util;

import org.william.racekart.converter.FunctionConverter;

/**
 * class used as annotation to convert time to long
 */
public class TimeConverter implements FunctionConverter<Long> {
    @Override
    public Long convert(String arg) {
        return TimeConverterUtil.parseTime(arg);
    }
}