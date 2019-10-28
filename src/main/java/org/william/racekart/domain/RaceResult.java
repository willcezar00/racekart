package org.william.racekart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RaceResult {

    private Integer position;

    private Pilot pilot;

    private Integer completedLaps;

    private String raceTotalTime;

    private String bestLap;

    private String avarageRaceSpeed;

    private Integer timeDifferenceToWinner;


}
