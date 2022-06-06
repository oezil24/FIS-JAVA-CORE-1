package com.fis.java;

/**
 * com.fis.java.Stack
 * Author TungHuynh - sondt18@fpt.com.vn
 * Date 16-Mar-22 04:42 PM
 */
public class Stack<T> implements Collection<T> {
    //Last-IN First-OUT
    private Node<T> top = null;

    @Override
    public void push(T value) {
        top = new Node<>(value, top);
    }

    @Override
    public T pop() {
        //Lay ra item Top
        T top = this.top.value;
        //Gan lai top cho item ke tiep
        this.top = this.top.next;
        return top;
    }

    @Override
    public boolean hasNext() {
        return top != null;
    }
}
