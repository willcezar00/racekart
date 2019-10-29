package org.william.racekart.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.william.racekart.converter.LogColumn;
import org.william.racekart.converter.LogCustomConverter;
import org.william.racekart.util.PilotConverter;
import org.william.racekart.util.TimeConverter;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = {"pilot", "lapNumber"})
public class LapLog {

    @LogCustomConverter(customConverter = TimeConverter.class)
    @LogColumn(name = "Hora", nullable = false)
    private Long eventTime;

    @LogCustomConverter(customConverter = PilotConverter.class)
    @LogColumn(name = "Piloto", nullable = false)
    private Pilot pilot;

    @LogColumn(name = "Nº Volta", nullable = false)
    private Integer lapNumber;

    @LogCustomConverter(customConverter = TimeConverter.class)
    @LogColumn(name = "Tempo Volta", nullable = false)
    private Long lapDuration;

    @LogColumn(name = "Velocidade média da volta", nullable = false)
    private BigDecimal averageSpeed;
}
