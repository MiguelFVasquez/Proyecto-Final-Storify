package co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;

import java.io.Serializable;

public class Node<T> implements Serializable {

    public T data;
    public Node<T> next;
    private Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
    public void imprimirDato() {
        System.out.println(this.getData());
    }

}