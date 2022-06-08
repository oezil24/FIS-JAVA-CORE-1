package com.fis.java;

public class Stack<T> implements Collection<T> {
    private Node<T> top = null;

    @Override
    public void push(T value) {
        top = new Node<>(value, top);
    }

    @Override
    public T pop() {
        T top = this.top.value;
        this.top = this.top.next;
        return top;
    }

    @Override
    public boolean hasNext() {
        return top != null;
    }
}
