package co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;

import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T>{

    public Node<T> first;
    public Node<T> last;
    private int size;

    public SimpleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public int getSize(){
        return  this.size;
    }
    public boolean isEmpty() {
        return first == null;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
        size++;
    }

    public void addEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public void delete(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (position == 0) {
            first = first.getNext();
        } else {
            Node current = first;
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
        Node current = first;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return (T) current.getData();
    }

    public boolean contains(T data){
        Node<T> current= first;
        while (current!=null){
            if (current.getData()==data){
                return true;
            }
            current= current.getNext();
        }
        return false;
    }

    public void traverse() {
        Node<T> current = first;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
    //Concatenar dos listas, no se permiten elementos repetidos
    public static <T> SimpleLinkedList <T> concatenate(SimpleLinkedList <T> lista1, SimpleLinkedList<T> list2){
        SimpleLinkedList<T> result= new SimpleLinkedList();
        Node<T> current= lista1.first;
        while (current!=null){
            result.addEnd(current.getData());
            current=current.getNext();
        }
        current= list2.first;
        while (current!=null){
            result.addEnd(current.getData());
            current=current.getNext();
        }


        return result;

    }

    public  void agregarLista(int position, SimpleLinkedList<T> lista){
        if (position<0 || position>=size){
            throw new IndexOutOfBoundsException("Invalid position");
        }

        if (lista == null || lista.isEmpty()) {
            // Si la lista a agregar está vacía o es nula, no hay nada que hacer
            return;
        }
        if (position == 0) {
            // Si la posición es 0, simplemente ajustamos los punteros de la nueva lista y la lista actual
            lista.last.setNext(first);
            first = lista.first;
            size += lista.getSize();
            return;
        }
        Node<T> current = first;
        // Mover a la posición anterior a la posición deseada
        for (int i = 0; i < position - 1; i++) {
            current = current.getNext();
        }

        Node<T> nextNode = current.getNext();
        // Conectar la última nodo de la nueva lista al nodo siguiente del nodo actual
        lista.last.setNext(nextNode);
        // Conectar el nodo actual al primer nodo de la nueva lista
        current.setNext(lista.first);
        // Incrementar el tamaño de la lista actual
        size += lista.getSize();

    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorSimpleList(first);
    }
    public class IteratorSimpleList implements Iterator<T>{
        private Node<T> node;
        private int posicion;

        public IteratorSimpleList(Node<T> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node!=null;
        }

        @Override
        public T next() {
            T value= node.getData();
            node= node.getNext();
            posicion++;
            return value;
        }

        public Node<T> getNode() {
            return node;
        }

        public void setNode(Node<T> node) {
            this.node = node;
        }

        public int getPosicion() {
            return posicion;
        }

        public void setPosicion(int posicion) {
            this.posicion = posicion;
        }

    }

}