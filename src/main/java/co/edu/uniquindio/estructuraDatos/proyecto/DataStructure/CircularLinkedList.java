package co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private int size;

    public CircularLinkedList() {
        first = null;
        size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
            first.setNext(first); // El siguiente del último elemento apunta a la cabeza
        } else {
            newNode.setNext(first.getNext());
            first.setNext(newNode);
        }
        size++;
    }

    public void delete(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (position == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<T> current = first;
                while (current.getNext() != first) {
                    current = current.getNext();
                }
                current.setNext(first.getNext());
                first = first.getNext();
            }
        } else {
            Node<T> current = first;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        size--;
    }

    public T get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        Node<T> current = first;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularLinkedListIterator<>();
    }
    private class CircularLinkedListIterator<T> implements Iterator<T> {
        private Node<T> current = (Node<T>) first;
        private int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            count--;
            return data;
        }
    }
}