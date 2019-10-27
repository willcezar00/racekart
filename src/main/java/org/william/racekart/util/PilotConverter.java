package org.william.racekart.util;

import org.william.racekart.converter.FunctionConverter;
import org.william.racekart.domain.Pilot;


public class PilotConverter implements FunctionConverter<Pilot> {

    @Override
    public Pilot convert(String arg) throws Exception {
        if (StringUtil.isNullOrEmpty(arg)) return null;
        String[] tokens = arg.split("-");
        return new Pilot(tokens[0].trim(), tokens[1].trim());
    }
}
