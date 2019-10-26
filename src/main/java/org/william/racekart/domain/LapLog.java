package org.william.racekart.domain;

import lombok.Getter;
import lombok.Setter;
import org.william.racekart.util.LogColumn;
import org.william.racekart.util.LogCustomConverter;
import org.william.racekart.util.TimeConverter;

import java.math.BigDecimal;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class LapLog {

    @LogCustomConverter(customConverter = TimeConverter.class)
    @LogColumn(name = "Hora")
    private Long eventTime;

    @LogColumn(name = "Piloto")
    private Pilot pilot;

    @LogColumn(name = "Nº Volta")
    private Integer lapNumber;

    @LogColumn(name = "Tempo Volta")
    private Long lapDuration;

    @LogColumn(name = "Velocidade média da volta")
    private BigDecimal avarageVelocity;
}
