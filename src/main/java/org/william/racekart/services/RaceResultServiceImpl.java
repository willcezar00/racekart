package org.william.racekart.services;

import lombok.Setter;
import org.william.racekart.domain.LapLog;
import org.william.racekart.domain.Pilot;
import org.william.racekart.domain.PilotRaceLog;
import org.william.racekart.repositories.FileRepository;
import org.william.racekart.repositories.FileRepositoryImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
public class RaceResultServiceImpl implements RaceResultService {

    private static RaceResultServiceImpl raceResultService = null;
    private FileRepository fileRepository;

    private RaceResultServiceImpl() {
        setFileRepository(FileRepositoryImpl.getInstance());
    }

    @Override
    public String getResults() {
        FileRepository fileRepository = new FileRepositoryImpl();
        List<LapLog> lapsLog = fileRepository.read("path", LapLog.class);
        Map<Pilot, List<LapLog>> pilotLaps = groupingLapsByPilot(lapsLog);
        List<PilotRaceLog> pilotRaceLogs = getPilotRaceResult(pilotLaps);
        Long bestLap = getBestLap(lapsLog);
        Collections.sort(pilotRaceLogs);
        getPositionAndDifferenceTime(pilotRaceLogs);

        return null;
    }

    private void getPositionAndDifferenceTime(List<PilotRaceLog> pilotRaceLogs) {
        Long raceTimeOfLastPilot = pilotRaceLogs.get(0).getRaceTime();
        for (int i = 0; i < pilotRaceLogs.size(); i++) {
            pilotRaceLogs.get(i).setPosition(i);
            pilotRaceLogs.get(i).setWinnerDifference(raceTimeOfLastPilot - pilotRaceLogs.get(i).getRaceTime());
        }
    }

    private Map<Pilot, List<LapLog>> groupingLapsByPilot(List<LapLog> lapsLog) {
        return lapsLog.stream().collect(Collectors.groupingBy(LapLog::getPilot));
    }

    private List<PilotRaceLog> getPilotRaceResult(Map<Pilot, List<LapLog>> lapsByPilot) {
        List<PilotRaceLog> pilotRaceLogs = new ArrayList<>();
        for (Map.Entry<Pilot, List<LapLog>> entry : lapsByPilot.entrySet()) {
            PilotRaceLog pilotRaceLog = new PilotRaceLog();
            pilotRaceLog.setPilot(entry.getKey());
            pilotRaceLog.setCompletedLaps(entry.getValue().size());
            pilotRaceLog.setAverageSpeed(entry.getValue().stream()
                    .map(LapLog::getAverageSpeed)
                    .reduce(BigDecimal::add)
                    .get().divide(new BigDecimal(pilotRaceLog.getCompletedLaps())));
            pilotRaceLog.setRaceTime(entry.getValue().stream().mapToLong(x -> x.getLapDuration()).sum());
            pilotRaceLog.setBestLap(getBestLap(entry.getValue()));
        }
        return pilotRaceLogs;
    }

    private Long getBestLap(List<LapLog> lapLogs) {
        return lapLogs.stream().mapToLong(LapLog::getLapDuration).min().getAsLong();
    }

    public static RaceResultServiceImpl getInstance() {
        if (raceResultService.equals(null)) {
            raceResultService = new RaceResultServiceImpl();
        }
        return raceResultService;

    }

}
