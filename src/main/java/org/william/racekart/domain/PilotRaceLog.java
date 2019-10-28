package org.william.racekart.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PilotRaceLog implements Comparable<PilotRaceLog> {

    private Integer position;

    private List<LapLog> lapslogs;

    private Pilot pilot;

    private Integer completedLaps;

    private BigDecimal averageSpeed;

    private Long raceTime;

    private Long bestLap;

    private Long winnerDifference;

    @Override
    public int compareTo(PilotRaceLog o) {
        if (o.getRaceTime() == null) return -1;
        if (getRaceTime() == null) return 1;
        return getRaceTime().compareTo(o.getRaceTime());
    }
}
