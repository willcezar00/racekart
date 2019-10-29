package org.william.racekart.services;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.william.racekart.domain.LapLog;
import org.william.racekart.domain.Pilot;
import org.william.racekart.domain.PilotRaceLog;
import org.william.racekart.domain.RaceResult;
import org.william.racekart.repositories.FileRepository;
import org.william.racekart.repositories.FileRepositoryImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PRIVATE)
public class RaceResultServiceImpl implements RaceResultService {

    private static RaceResultServiceImpl raceResultService = null;
    private FileRepository fileRepository;

    private RaceResultServiceImpl() {
        setFileRepository(FileRepositoryImpl.getInstance());
    }

    @Override
    public RaceResult getResults(String path) {
        List<LapLog> lapsLog = getFileRepository().read(path, LapLog.class);
        Map<Pilot, List<LapLog>> pilotLaps = groupingLapsByPilot(lapsLog);
        List<PilotRaceLog> pilotRaceLogs = getPilotRaceResult(pilotLaps);
        LapLog bestLap = getBestLap(lapsLog);
        Collections.sort(pilotRaceLogs);
        getPositionAndDifferenceTime(pilotRaceLogs);

        return new RaceResult(bestLap, pilotRaceLogs);
    }

    @Override
    public void writeResults(String path, RaceResult raceResult) {
        getFileRepository().write(path, raceResult);
    }

    private void getPositionAndDifferenceTime(List<PilotRaceLog> pilotRaceLogs) {
        PilotRaceLog winner = pilotRaceLogs.get(0);
        winner.setPosition(1);
        winner.setWinnerDifference(0L);
        for (int i = 1; i < pilotRaceLogs.size(); i++) {
            PilotRaceLog pilot = pilotRaceLogs.get(i);
            if (pilot.getRaceTime() == pilotRaceLogs.get(i - 1).getRaceTime()) {
                pilot.setPosition(pilotRaceLogs.get(i - 1).getPosition());
            } else {
                pilot.setPosition(i + 1);
            }
            pilot.setWinnerDifference(pilotRaceLogs.get(i).getRaceTime() - winner.getRaceTime());
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
                    .get().divide(BigDecimal.valueOf(pilotRaceLog.getCompletedLaps()),3, RoundingMode.CEILING));
            pilotRaceLog.setRaceTime(entry.getValue().stream().mapToLong(x -> x.getLapDuration()).sum());
            pilotRaceLog.setBestLap(getBestLapTime(entry.getValue()));
            pilotRaceLogs.add(pilotRaceLog);
        }
        return pilotRaceLogs;
    }

    private Long getBestLapTime(List<LapLog> lapLogs) {
        return lapLogs.stream().mapToLong(LapLog::getLapDuration).min().getAsLong();
    }

    private LapLog getBestLap(List<LapLog> lapLogs) {
        return lapLogs.stream().min(Comparator.comparing(LapLog::getLapDuration)).get();
    }

    public static RaceResultServiceImpl getInstance() {
        if (raceResultService == null) {
            raceResultService = new RaceResultServiceImpl();
        }
        return raceResultService;

    }

}
