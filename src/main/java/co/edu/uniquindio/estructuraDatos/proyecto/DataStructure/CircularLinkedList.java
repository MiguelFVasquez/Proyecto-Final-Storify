package co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;

import co.edu.uniquindio.estructuraDatos.proyecto.model.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements Iterable<T> , Serializable {
    private Node<T> first;
    private int size;

    /**
     * Constructor de la lista circulas
     */
    public CircularLinkedList() {
        first = null;
        size = 0;
    }

    /**
     * Metodo que agreaga los datos a la lista
     * @param data
     */
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

    /**
     * Metodo que elimina los datos de la lista segun una posicion especifica
     * @param position
     */
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

    /**
     * Metodo para eliminar recibiendo un dato
     * @param data
     */
    public void delete(T data) {
        if (size == 0) {
            return; // La lista está vacía, no hay nada que eliminar
        }

        Node<T> current = first;
        Node<T> previous = null;

        // Buscar el nodo que contiene el dato
        do {
            if (current.getData().equals(data)) {
                // El nodo actual contiene el dato que queremos eliminar
                if (previous == null) {
                    // El nodo a eliminar es el primero
                    if (size == 1) {
                        // Si solo hay un nodo en la lista
                        first = null;
                    } else {
                        // Si hay más de un nodo, ajustar las referencias
                        Node<T> last = first;
                        while (last.getNext() != first) {
                            last = last.getNext();
                        }
                        last.setNext(first.getNext());
                        first = first.getNext();
                    }
                } else {
                    // El nodo a eliminar no es el primero
                    previous.setNext(current.getNext());
                }
                size--;
                return; // Nodo eliminado, terminar el método
            }
            previous = current;
            current = current.getNext();
        } while (current != first); // Parar cuando volvamos al principio de la lista

        // Si llegamos aquí, el dato no se encontró en la lista
        throw new NoSuchElementException("Element not found: " + data.toString());
    }

    /**
     * Metodo que obtiene la posicion de un nodo
     * @param position
     * @return
     */
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

    /**
     * Metodo que obtiene el tamañao de la lista
     * @return
     */
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
    public List<Song> toList(){
        List<Song> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Song song = (Song) get( i );
            if(song!=null){
                list.add( song );
            }

        }
        return list;
    }
    public void toCircular(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            add( (T) list.get( i ) );
        }
    }


}