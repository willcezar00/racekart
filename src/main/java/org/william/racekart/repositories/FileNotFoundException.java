package org.william.racekart.repositories;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class FileNotFoundException extends RuntimeException {

    private String path;

    public FileNotFoundException(String path) {
        super(MessageFormat.format("file not found  at {0}", path));
        setPath(path);
    }

}
