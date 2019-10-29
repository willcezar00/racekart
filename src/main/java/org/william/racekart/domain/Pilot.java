package org.william.racekart.domain;

import lombok.*;

@Data
@EqualsAndHashCode(of = "code")
@AllArgsConstructor
public class Pilot {

    private String code;

    private String name;
}
