package org.william.racekart.repositories;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class OutputException extends RuntimeException {

    private String path;

    public OutputException(String path) {
        super(MessageFormat.format("Could not write file from  {0}", path == null ? "System.out" : path));
        setPath(path);
    }
}
