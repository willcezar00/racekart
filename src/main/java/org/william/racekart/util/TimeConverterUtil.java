package org.william.racekart.util;

import org.william.racekart.converter.ParseException;

import java.text.DateFormat;
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
    public static Long parseTime(String time) throws Exception {
        if (StringUtil.isNullOrEmpty(time)) return null;
        Long finalTime = 0L;
        String[] tokens = time.split(TIME_SEPARATOR_REGEX);
        for (int i = 0; i < time.length(); i++) {
            finalTime += Long.parseLong(tokens[i]) * TIME_MULTIPLIERS[i];
        }
        return finalTime;
    }

    public static String getTimeByLong(Long durationInMillis){
        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    private TimeConverterUtil() {
    }

}
