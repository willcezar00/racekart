package org.william.racekart.util;

import org.william.racekart.converter.FunctionConverter;
import org.william.racekart.converter.ParseException;

import java.util.function.Function;

/**
 * class used as annotation to convert time to long
 */
public class TimeConverter implements FunctionConverter<Long> {
    @Override
    public Long convert(String arg) throws Exception {
        return TimeConverterUtil.parseTime(arg);
    }
}