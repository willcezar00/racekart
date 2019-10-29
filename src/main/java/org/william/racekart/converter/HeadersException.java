package org.william.racekart.converter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Arrays;

@Getter
@Setter(AccessLevel.PRIVATE)
public class HeadersException extends RuntimeException {
    private String[] headersNotFound;

    public HeadersException(String[] headersNotFound) {
        super(MessageFormat.format("Headers do not match expected. Headers not found {0}", Arrays.toString(headersNotFound)));
        setHeadersNotFound(headersNotFound);
    }
}
