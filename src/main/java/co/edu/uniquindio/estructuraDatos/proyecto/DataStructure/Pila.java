package co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;

import java.io.Serializable;
import java.util.NoSuchElementException;

class Pila<T> implements Serializable {
    private Node<T> cima;

    public Pila() {
        this.cima = null;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public void apilar(T dato) {
        Node<T> nuevoNodo = new Node<>(dato);
        if (estaVacia()) {
            cima = nuevoNodo;
        } else {
            nuevoNodo.setNext(cima);
            cima = nuevoNodo;
        }
    }

    public T desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        T datoDesapilado = cima.getData();
        cima = cima.getNext();
        return datoDesapilado;
    }

    public T verCima() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        return cima.getData();
    }
}