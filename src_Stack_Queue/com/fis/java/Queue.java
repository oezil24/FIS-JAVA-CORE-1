package com.fis.java;

/**
 * com.fis.java.Queue
 * Author TungHuynh - sondt18@fpt.com.vn
 * Date 16-Mar-22 04:42 PM
 */
public class Queue<T> implements Collection<T> {
    //First-IN First-OUT
    private Node<T> front = null;
    private Node<T> rear = null;

    @Override
    public void push(T value) {
        if (rear == null) {
            rear = new Node<>(value, null);
            front = rear;
        } else {
            Node<T> temp = new Node<>(value, null);
            rear.next = temp;
            rear = temp;
        }
    }

    @Override
    public T pop() {
        //Lay ra item Top
        T top = front.value;
        //Gan lai top cho item ke tiep
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return top;
    }

    @Override
    public boolean hasNext() {
        return front != null;
    }
}
