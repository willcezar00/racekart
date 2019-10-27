package org.william.racekart.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class ArrayUtil {

    public static <TYPE> Map<TYPE, Integer> mapIndexByElement(TYPE[] array) {
        Map<TYPE, Integer> indexByElement = new HashMap<>();
        for (int position = 0; position < array.length; position++) {
            indexByElement.put(array[position], position);
        }
        return indexByElement;
    }
}
