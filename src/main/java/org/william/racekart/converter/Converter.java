package org.william.racekart.converter;

import java.util.Set;

public interface Converter<TYPE> {
    TYPE convert(String[] args);

    Set<String> getIgnoreHeadersFromFile();

    Set<String> getIgnoreHeadersFromClass();
}
