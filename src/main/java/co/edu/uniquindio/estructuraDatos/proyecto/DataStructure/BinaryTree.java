package co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree <T extends Comparable<T> > implements Iterable<T>{
    private Node<T> root;

    public BinaryTree() {
        this.root=null;
    }

    /**
     * Metodo para comprobar si un elemento ya está en el arbol
     * @param search
     * @return
     */
    public boolean exist(T search){
        return exist(this.root, search);
    }

    /**
     * Método privado para comprobar la existencia de un elemento en el árbol
     * @param node
     * @param search
     * @return
     */

    public boolean exist(Node<T> node, T search){
        if (node==null){
            return false;
        }
        if (node.getData().compareTo(search) == 0) {
            return true;
        } else if (search.compareTo(node.getData()) < 0) {
            return exist(node.getPrev(), search);
        } else {
            return exist(node.getNext(), search);
        }
    }

    /**
     *Inserta (añade un elemento al arbol)
     * @param dato
     */
    public void insertar(T dato) {
        if (this.root == null) {
            this.root = new Node<>(dato);
        } else {
            this.insertar(this.root, dato);
        }
    }

    /**
     * Metodo privado que contiene la logica de como añadir un elemento al arbol
     * @param head
     * @param data
     */
    private void insertar(Node<T> head, T data) {
        if (data.compareTo(head.getData()) > 0) {
            if (head.getNext() == null) {
                head.setNext(new Node<>(data));
            } else {
                this.insertar(head.getNext(), data);
            }
        } else {
            if (head.getPrev() == null) {
                head.setPrev(new Node<>(data));
            } else {
                this.insertar(head.getPrev(), data);
            }
        }
    }


    public void eliminar(T dato) {
        root = eliminarNodo(root, dato);
    }

    private Node<T> eliminarNodo(Node<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        }

        // Buscar el nodo que se quiere eliminar
        if (dato.compareTo(nodo.getData()) < 0) {
            nodo.setPrev(eliminarNodo(nodo.getPrev(), dato));
        } else if (dato.compareTo(nodo.getData()) > 0) {
            nodo.setNext(eliminarNodo(nodo.getNext(), dato));
        } else {
            // Caso 1: Nodo sin hijos
            if (nodo.getPrev() == null && nodo.getNext() == null) {
                return null;
            }
            // Caso 2: Nodo con un hijo
            else if (nodo.getPrev() == null) {
                return nodo.getNext();
            } else if (nodo.getNext() == null) {
                return nodo.getPrev();
            }
            // Caso 3: Nodo con dos hijos
            else {
                // Encontrar el sucesor inmediato en el subárbol derecho
                Node<T> sucesor = encontrarSucesor(nodo.getNext());
                nodo.setData(sucesor.getData());
                // Eliminar el sucesor del subárbol derecho
                nodo.setNext(eliminarNodo(nodo.getNext(), sucesor.getData()));
            }
        }
        return nodo;
    }

    private Node<T> encontrarSucesor(Node<T> nodo) {
        Node<T> actual = nodo;
        while (actual.getPrev() != null) {
            actual = actual.getPrev();
        }
        return actual;
    }
    /**
     * Realiza el recorrido preorden en el árbol, comenzando desde el nodo dado
     * @param n
     */
    private void preorden(Node n) {
        if (n != null) {
            n.imprimirDato();
            preorden(n.getPrev());
            preorden(n.getNext());
        }
    }

    /**
     *  Realiza el recorrido inorden en el árbol, comenzando desde el nodo dado
     * @param n
     */
    private void inorden(Node n) {
        if (n != null) {
            inorden(n.getPrev());
            n.imprimirDato();
            inorden(n.getNext());
        }
    }

    /**
     * Realiza el recorrido postorden en el árbol, comenzando desde el nodo dado
     * @param n
     */
    private void postorden(Node n) {
        if (n != null) {
            postorden(n.getPrev());
            postorden(n.getNext());
            n.imprimirDato();
        }
    }

    // Inicia el recorrido preorden en el árbol comenzando desde la raíz
    public void preorden() {
        this.preorden(this.root);
    }

    // Inicia el recorrido inorden en el árbol comenzando desde la raíz
    public void inorden() {
        this.inorden(this.root);
    }

    // Inicia el recorrido postorden en el árbol comenzando desde la raíz
    public void postorden() {
        this.postorden(this.root);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArbolIterator(root);
    }

    private class ArbolIterator implements Iterator<T> {
        private final Pila<Node<T>> pila;

        public ArbolIterator(Node<T> raiz) {
            pila = new Pila<>();
            agregarNodos(raiz);
        }

        private void agregarNodos(Node<T> nodo) {
            while (nodo != null) {
                pila.apilar(nodo);
                nodo = nodo.getPrev();
            }
        }

        @Override
        public boolean hasNext() {
            return !pila.estaVacia();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> nodoActual = pila.desapilar();
            agregarNodos(nodoActual.getNext());
            return nodoActual.getData();
        }
    }
}
