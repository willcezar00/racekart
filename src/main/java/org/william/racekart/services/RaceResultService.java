package org.william.racekart.services;

import org.william.racekart.domain.RaceResult;

public interface RaceResultService {
    RaceResult getResults(String path);

    void writeResults(String path, RaceResult raceResult);
}
