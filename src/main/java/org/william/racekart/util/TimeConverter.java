package org.william.racekart.util;

import java.util.function.Function;

public  class TimeConverter implements Function<String, Long> {
    @Override
    public Long apply(String o) {
        return TimeConverterUtil.parseTime(o);
    }
}