package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter(AccessLevel.PRIVATE)
public class InvalidRowFormatException extends RuntimeException {
    private int headerColumnSize;
    private int rowColumnSize;

    public InvalidRowFormatException(int headerColumnSize, int rowColumnSize) {
        super(MessageFormat.format("The row size expected was {0} but got {1}", headerColumnSize, rowColumnSize));
        setHeaderColumnSize(headerColumnSize);
        setRowColumnSize(rowColumnSize);
    }
}

