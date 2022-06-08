package com.fis.java;


public interface Collection<T> {
    void push(T value);
    T pop();
    boolean hasNext();
}
