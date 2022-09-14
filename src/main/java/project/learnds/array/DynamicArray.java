package project.learnds.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * My implementation of a dynamic array (ArrayList).
 * Allows for null values.
 */
public class DynamicArray<E> implements Iterable<E> {

    /**
     * Default initial capacity.
     */
    private static final int INIT_CAPACITY = 16;

    /**
     * Array elements.
     */
    private Object[] elements;

    /**
     * Number of elements in this list.
     */
    private int size;

    /**
     * Default constructor that uses {@code INIT_CAPACITY} as its inital capacity.
     */
    public DynamicArray() {
        this(INIT_CAPACITY);
    }

    /**
     * Constuctor that uses {@code capacity} as its initial capacity.
     * 
     * @param capacity initial capacity
     */
    public DynamicArray(int capacity) {
        elements = new Object[capacity];
    }

    /**
     * Appends the specified element {@code e} to the end of the list.
     * 
     * @param e element to be appeneded to this list
     * @return true
     */
    public boolean add(E e) {
        // double array's capacity if full
        if (size >= elements.length) {
            Object[] temp = new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                temp[i] = elements[i];
            }
            elements = temp;
        }

        elements[size] = e;
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
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkBounds(index);

        return (E) elements[index];
    }

    /**
     * Returns the index of the first occurrence of {@code o} or -1 if this list doesn't contain it.
     * 
     * @param o element to search for
     * @return index of the first occcurrence of {@code o} or -1 if this list doesn't contian it
     */
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if ((e == null && elements[i] == null) || (e != null && e.equals(elements[i]))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Removes the element at position {@code index}. Shifts any subsequent elements to the left.
     * Returns the element that was removed.
     * 
     * @param index
     * @return the element previously at position {@code index}
     * @throws ArrayIndexOutOfBoundsException if {@code index} is out of range
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkBounds(index);

        E retVal = (E) elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }

        elements[size - 1] = null;
        size--;

        return retVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size - 1; i++) {
            if (elements[i] == null) {
                sb.append("null");
            } else {
                sb.append(elements[i].toString());
            }
            
            sb.append(", ");
        }

        if (size > 0) {
            if (elements[size - 1] == null) {
                sb.append("null");
            } else {
                sb.append(elements[size - 1].toString());
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
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                if (index >= size) {
                    throw new NoSuchElementException();
                }

                return (E) elements[index++];
            }
        };
    }
}