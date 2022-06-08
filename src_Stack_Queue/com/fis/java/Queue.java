package com.fis.java;

public class Queue<T> implements Collection<T> {
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
        T top = front.value;
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
