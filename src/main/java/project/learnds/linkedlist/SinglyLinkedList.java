package project.learnds.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * My implementation of a singly linked list. Allows for null values.
 */
public class SinglyLinkedList<E> implements Iterable<E> {
    
    /**
     * Dummy head node. Eliminates having to check for null value 
     * when adding or removing the head.
     */
    private Node<E> dhead;

    /**
     * Tail node.
     */
    private Node<E> tail;

    /**
     * Number of elements in this list.
     */
    private int size;

    /**
     * Sole constructor.
     */
    public SinglyLinkedList() {
        dhead = new Node<>(null);
        tail = dhead;
    }

    /**
     * Appends the specified element {@code e} to the end of the list.
     * 
     * @param e element to be appended to this list
     * @return true
     */
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        tail.next = newNode;
        tail = newNode; 

        size++;       

        return true;
    }

    /**
     * Returns the element at position {@code index}.
     * 
     * @param index index of the element to return
     * @return element at position {@code index}
     * @throws ArrayIndexOutOfBoundsException if {@code index} is out of rage
     */
    public E get(int index) {
        checkBounds(index);

        if (index == (size  - 1)) {
            return tail.item;
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
     * @return index of the first occurrence of {@code e} or -1 if this list doesn't contain it.
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

        Node<E> curr = dhead;
        for (int i = -1; i < (index - 1); i++) {
            curr = curr.next;
        }

        Node<E> retVal = curr.next;
        curr.next = curr.next.next;

        if (retVal == tail) {
            tail = curr;
        }
        
        size--;

        return retVal.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        Node<E> curr = dhead.next;
        while (curr != null && curr.next != null) {
            if (curr.item == null) {
                sb.append("null");
            } else {
                sb.append(curr.item.toString());
            }
            
            sb.append(" -> ");

            curr = curr.next;
        }

        if (curr != null) {
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
                return curr != null;
            }

            @Override
            public E next() {
                if (curr == null) {
                    throw new NoSuchElementException();
                }

                E retVal = curr.item;
                curr = curr.next;

                return retVal;
            }
        };
    }
}
