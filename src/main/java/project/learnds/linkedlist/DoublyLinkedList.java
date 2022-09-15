package project.learnds.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class representing a doubly linked list. Allows for null values.
 */
public class DoublyLinkedList<E> implements Iterable<E> {
    
    /**
     * Class that represents information with a single element in a doubly linked list.
     */
    private class Node<T> {

        /**
         * Data for this node.
         */
        T item;

        /**
         * Pointer to next node.
         */
        Node<T> next;

        /**
         * Pointer to previous node.
         */
        Node<T> prev;

        /**
         * Default constructor using specified {@code item}. Sets {@code next} and {@code prev} pointers to null.
         * @param item
         */
        public Node(T item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    /**
     * Dummy head node. Eliminates having to check for null values
     * when adding or removing the head.
     */
    private Node<E> dhead;

    /**
     * Dummy tail node. Eliminates having to check for null values
     * when adding or removing the tail.
     */
    private Node<E> dtail;

    /**
     * Number of elements in this list.
     */
    private int size;

    /**
     * Sole constructor.
     */
    public DoublyLinkedList() {
        dhead = new Node<>(null);
        dtail = new Node<>(null);
        dhead.next = dtail;
        dtail.prev = dhead;
    }

    /**
     * Appends the specified element {@code e} to the end of the list.
     * 
     * @param e element to be appended to this list
     * @return true
     */
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        dtail.prev.next = newNode;
        newNode.prev = dtail.prev;
        newNode.next = dtail;
        dtail.prev = newNode;

        size++;

        return true;
    }

    /**
     * Returns the element at position {@code index}.
     * 
     * @param index index of the element to return
     * @return element at position {@code index}
     * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range
     */
    public E get(int index) {
        checkBounds(index);

        if (index == (size - 1)) {
            return dtail.prev.item;
        }

        Node<E> curr = dhead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.item;        
    }

    /**
     * Returns the index of the first occurrence of {@code e} or -1 if this list doesn't contain it.
     * 
     * @param e element to search for
     * @return index of the first occurrence of {@code e} or -1 if this list doesn't contain it
     */
    public int indexOf(E e) {
        if (isEmpty()) {
            return -1;
        }

        Node<E> curr = dhead.next;
        for (int i = 0; i < size; i++) {
            if ((e == null && curr.item == null) || (e != null && e.equals(curr.item))) {
                return i;
            }

            curr = curr.next;
        }

        return -1;
    }

    /**
     * Removes the element at position {@code index}. 
     * Returns the element that was removed.
     * 
     * @param index index of the element to remove
     * @return the element previously at position {@code index}
     * @throws ArrayIndexOutOfBoundsException if {@code index} is out of rage
     */
    public E remove(int index) {
        checkBounds(index);

        Node<E> curr = dhead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        curr.next = null;
        curr.prev = null;

        size--;

        return curr.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> curr = dhead.next;
        while (curr != dtail && curr.next != dtail) {
            if (curr.item == null) {
                sb.append("null");
            } else {
                sb.append(curr.item.toString());
            }

            sb.append(" <-> ");

            curr = curr.next;
        }

        if (curr.next == dtail) {
            if (curr.item == null) {
                sb.append("null");
            } else {
                sb.append(curr.item.toString());
            }
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     * 
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Checks if {@code index} is out of range.
     * 
     * @param index index to check
     * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range
     */
    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> curr = dhead.next;

            @Override
            public boolean hasNext() {
                return curr != dtail;
            }

            @Override
            public E next() {
                if (curr == dtail) {
                    throw new NoSuchElementException();
                }

                E retVal = curr.item;
                curr = curr.next;

                return retVal;
            }
        };
    }
}
