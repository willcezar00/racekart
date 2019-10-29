package org.william.racekart.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(of = "pilot")
@ToString(exclude = "lapLogs")
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
        int result = getRaceTime().compareTo(o.getRaceTime());
        return result == 0 ? getPilot().getName().compareTo(o.getPilot().getName()) : result;
    }
}
