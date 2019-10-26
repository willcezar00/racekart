package org.william.racekart.app;

import org.william.racekart.domain.LapLog;
import org.william.racekart.converter.LogColumn;

import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println(LapLog.class.getDeclaredField("eventTime").getAnnotation(LogColumn.class).name());
        System.out.println(Arrays.toString("1:02.852".split("[\\.:]")));
    }
}
