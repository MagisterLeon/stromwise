package com.stromwise.skilltree.parser;

public class TreeDeserializationException extends RuntimeException {

    private static final String MSG = "Cannot deserialize tree from json - %s";

    public TreeDeserializationException(final String treeJson, final Throwable cause) {
        super(String.format(MSG, treeJson), cause);
    }
}
