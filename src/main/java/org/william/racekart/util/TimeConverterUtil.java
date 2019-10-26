package org.william.racekart.util;

import java.util.function.Function;

public class TimeConverterUtil {

    private static final String TIME_SEPARATOR_REGEX = "[\\.:]";
    private static final long[] TIME_MULTIPLIERS = { 360_000L,60_000L,1_000L,1L };

    public static Long  parseTime(String time){
        Long finalTime = 0L;
        String[] tokens =  time.split(TIME_SEPARATOR_REGEX);
        for(int i  = 0; i < time.length(); i++){
            finalTime += Long.parseLong(tokens[i])  * TIME_MULTIPLIERS[i] ;
        }
        return finalTime;
    }

    private TimeConverterUtil(){
    }
}
