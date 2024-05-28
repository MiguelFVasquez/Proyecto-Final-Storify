package co.edu.uniquindio.estructuraDatos.proyecto.DataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> , Serializable {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public DoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }
    public boolean contains(T data) {
        Node<T> current = first;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first.setPrev(newNode);
            first = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrev(last);
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
            if (first != null) {
                first.setPrev(null);
            }
        } else if (position == size - 1) {
            last = last.getPrev();
            last.setNext(null);
        } else {
            Node<T> current = first;
            for (int i = 0; i < position; i++) {
                current = current.getNext();
            }
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        size--;
    }

    public void delete(T data) {
        Node<T> current = first;
        while (current != null) {
            if (current.getData().equals(data)) { // Comprobamos si el dato del nodo actual es igual al dato que queremos eliminar
                if (current == first) {
                    first = current.getNext(); // Si el nodo a eliminar es el primero, actualizamos el puntero first
                }
                if (current == last) {
                    last = current.getPrev(); // Si el nodo a eliminar es el último, actualizamos el puntero last
                }
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext()); // Enlazamos el nodo anterior con el siguiente
                }
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev()); // Enlazamos el nodo siguiente con el anterior
                }
                size--; // Reducimos el tamaño de la lista

                return; // Terminamos el método después de eliminar el nodo
            }
            current = current.getNext(); // Avanzamos al siguiente nodo
        }
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

    public void agregarEnOrden(int dato, DoublyLinkedList<Integer> lista) {
        Node<Integer> nuevoNodo = new Node<>(dato);

        // Si la lista está vacía o el primer elemento es mayor que el dato a insertar, insertamos al inicio
        if (lista.first == null || lista.first.getData() >= dato) {
            nuevoNodo.setNext(lista.first);
            if (lista.first != null) {
                lista.first.setPrev(nuevoNodo);
            }
            lista.first = nuevoNodo;
            lista.size++;
            return;
        }

        // Recorremos la lista para encontrar la posición donde insertar el nuevo dato
        Node<Integer> current = lista.first;
        while (current.getNext() != null && current.getNext().getData() < dato) {
            current = current.getNext();
        }

        // Insertamos el nuevo dato después del nodo current
        nuevoNodo.setNext(current.getNext());
        if (current.getNext() != null) {
            current.getNext().setPrev(nuevoNodo);
        }
        current.setNext(nuevoNodo);
        nuevoNodo.setPrev(current);

        lista.size++;
    }


    public void printList(){
        Node<T> current= first;
        while (current!=null){
            System.out.print(current.getData()+ " ");
            current=current.getNext();
        }
        System.out.println();

    }


    public void printListReverse(){
        Node<T> current= last;
        while(current!=null){
            System.out.print(current.getData() + " ");
            current=current.getPrev();
        }
        System.out.println();
    }


    public int getSize() {
        return size;
    }


    public int distanciaMaximaEntreElementos(int x) {
        int[] maxDistance = {0}; // Usamos un array para almacenar la distancia máxima (mutable)
        distanciaMaximaRecursiva(first, x, 0, maxDistance);
        return maxDistance[0];
    }

    private void distanciaMaximaRecursiva(Node<T> current, int x, int currentDistance, int[] maxDistance) {
        if (current == null) {
            return; // Si llegamos al final de la lista, terminamos la recursión
        }
        if ((Integer) (current.getData()) == x ) {
            // Si encontramos un elemento con valor x, actualizamos la distancia máxima si es necesario
            maxDistance[0] = Math.max(maxDistance[0], currentDistance);
        }
        // Llamada recursiva al siguiente nodo, incrementando la distancia actual si no se ha encontrado el valor x
        distanciaMaximaRecursiva(current.getNext(), x, (Integer) (current.getData()) == x ? 0 : currentDistance + 1, maxDistance);
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>(); // Crea una lista para contener los elementos
        Node<T> current = first; // Comienza desde el primer nodo
        while (current != null) {
            list.add(current.getData()); // Añade los datos del nodo actual a la lista
            current = current.getNext(); // Avanza al siguiente nodo
        }
        return list; // Devuelve la lista con todos los elementos de la lista doblemente enlazada
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }






}