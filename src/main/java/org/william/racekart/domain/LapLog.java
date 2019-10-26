package org.william.racekart.domain;

import lombok.Getter;
import lombok.Setter;
import org.william.racekart.converter.LogColumn;
import org.william.racekart.converter.LogCustomConverter;
import org.william.racekart.util.PilotConverter;
import org.william.racekart.util.TimeConverter;

import java.math.BigDecimal;

@Getter
@Setter
public class LapLog {

    @LogCustomConverter(customConverter = TimeConverter.class)
    @LogColumn(name = "Hora")
    private Long eventTime;

    @LogCustomConverter(customConverter = PilotConverter.class)
    @LogColumn(name = "Piloto")
    private Pilot pilot;

    @LogColumn(name = "Nº Volta")
    private Integer lapNumber;

    @LogCustomConverter(customConverter = TimeConverter.class)
    @LogColumn(name = "Tempo Volta")
    private Long lapDuration;

    @LogColumn(name = "Velocidade média da volta")
    private BigDecimal averageSpeed;
}
