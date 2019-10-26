package org.william.racekart.util;

import java.util.function.Function;

/**
 * class used as annotation to convert time to long
 */
public  class TimeConverter implements Function<String, Long> {
    @Override
    public Long apply(String o) {
        return TimeConverterUtil.parseTime(o);
    }
}