package org.william.racekart.repositories;

import org.william.racekart.domain.RaceResult;

import java.util.List;

public interface FileRepository {

    <TYPE> List<TYPE> read(String path, Class<TYPE> typeClass);

    void write(String path, RaceResult raceResult);

}
