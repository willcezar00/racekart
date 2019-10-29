package org.william.racekart.repositories;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class InputException extends RuntimeException {

    private String path;

    public InputException(String path) {
        super(MessageFormat.format("Could not read file from  {0}", path == null ? "System.in" : path));
        setPath(path);
    }
}
