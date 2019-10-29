package org.william.racekart.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeConverterUtil {

    private static final String TIME_SEPARATOR_REGEX = "[\\.:]";
    private static final long[] TIME_MULTIPLIERS = {360_000L, 60_000L, 1_000L, 1L};

    /**
     * convert a time string to long, multiplaing each part of time with representing time multiplier
     *
     * @param time string that representing time. ex 1:02.852
     * @return long representing the time
     */
    public static Long parseTime(String time) {
        if (StringUtil.isNullOrEmpty(time)) return null;
        Long finalTime = 0L;
        String[] tokens = time.split(TIME_SEPARATOR_REGEX);
        int multiplierIndex = TIME_MULTIPLIERS.length - tokens.length;
        for (int i = 0; i < tokens.length; i++) {
            finalTime += Long.parseLong(tokens[i]) * TIME_MULTIPLIERS[multiplierIndex];
            multiplierIndex++;
        }
        return finalTime;
    }

    public static String getTimeByLong(Long durationInMillis) {
        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        if (hour > 0) {
            return String.format("%d:%02d:%02d.%03d", hour, minute, second, millis);
        }

        return minute > 0 ? String.format("%d:%02d.%03d", minute, second, millis) : String.format("%d.%03d", second, millis);
    }

    public static String getTimeByLongWithDate(Long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    private TimeConverterUtil() {
    }

}
