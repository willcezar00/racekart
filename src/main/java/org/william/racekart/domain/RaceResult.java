package org.william.racekart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RaceResult {

    private LapLog bestLap;

    private List<PilotRaceLog> pilotRaceLogs;
}
