package com.stromwise.skilltree.parser;

public interface Parser<T> {

    T parse(String json);
}
