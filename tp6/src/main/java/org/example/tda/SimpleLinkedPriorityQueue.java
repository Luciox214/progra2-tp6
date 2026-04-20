package org.example.tda;



import java.util.NoSuchElementException;

public class SimpleLinkedPriorityQueue<E> {
    private PriorityLinkedNode<E> first;
    private PriorityLinkedNode<E> last;
    private int size;

    public void enqueue(E element, int priority) {
        if (element == null) {
            throw new IllegalArgumentException("Error del TDA: No se pueden encolar elementos nulos.");
        }

        PriorityLinkedNode<E> newNode = new PriorityLinkedNode<>(element, priority);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            PriorityLinkedNode<E> current = last;
            while (current != null && priority < current.priority) {
                current = current.prev;
            }

            if (current == null) {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
            } else {
                newNode.next = current.next;
                newNode.prev = current;

                if (current.next != null) {
                    current.next.prev = newNode;
                } else {
                    last = newNode;
                }
                current.next = newNode;
            }
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error del TDA: La cola está vacía.");
        }
        E value = first.value;
        first = first.next;

        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        size--;
        return value;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error del TDA: La cola está vacía.");
        }
        return first.value;
    }

    public int getHighestPriority() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error del TDA: La cola está vacía.");
        }
        return first.priority;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
}