package org.william.racekart.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "code")
public class Pilot {

    private String code;

    private String name;

    public Pilot(String arg){
        String[] tokens = arg.split("-");
        setCode(tokens[0].trim());
        setName(tokens[1].trim());
    }
}
