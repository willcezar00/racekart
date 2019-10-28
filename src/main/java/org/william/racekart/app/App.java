package org.william.racekart.app;

import org.william.racekart.domain.LapLog;
import org.william.racekart.converter.LogColumn;
import org.william.racekart.services.RaceResultService;
import org.william.racekart.services.RaceResultServiceImpl;

import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {
        RaceResultService raceResultService = new RaceResultServiceImpl();
        raceResultService.getResults();
    }
}
