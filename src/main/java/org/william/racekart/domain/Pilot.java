package org.william.racekart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "code")
@AllArgsConstructor
public class Pilot {

    private String code;

    private String name;
}
