package com.fis.java;

/**
 * com.fis.java.Node
 * Author TungHuynh - sondt18@fpt.com.vn
 * Date 16-Mar-22 04:41 PM
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}
