package com.fis.java;

/**
 * com.fis.java.Collection
 * Author TungHuynh - sondt18@fpt.com.vn
 * Date 16-Mar-22 04:39 PM
 */
public interface Collection<T> {
    void push(T value);
    T pop();
    boolean hasNext();
}
