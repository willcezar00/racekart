package org.william.racekart.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "code")
@AllArgsConstructor
public class Pilot {

    private String code;

    private String name;
}
