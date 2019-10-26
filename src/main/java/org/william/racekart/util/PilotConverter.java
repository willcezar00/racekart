package org.william.racekart.util;

import org.william.racekart.domain.Pilot;

import java.util.function.Function;

public class PilotConverter implements Function<String, Pilot> {
    @Override
    public Pilot apply(String o) {
        return new Pilot(o);
    }
}
