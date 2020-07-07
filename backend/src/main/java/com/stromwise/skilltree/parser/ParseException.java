package com.stromwise.skilltree.parser;

public class ParseException extends RuntimeException {

    private static final String MSG = "Cannot deserialize from json - %s";

    public ParseException(final String json, final Throwable cause) {
        super(String.format(MSG, json), cause);
    }
}
