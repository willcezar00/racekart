package org.william.racekart.util;

public class StringUtil {

    public static String toFirstLetterUpperCase(String arg) {
        return isNullOrEmpty(arg) ? arg : Character.toUpperCase(arg.charAt(0)) + arg.substring(1);
    }

    public static boolean isNullOrEmpty(String arg) {
        return arg == null || arg.isEmpty();
    }
}
