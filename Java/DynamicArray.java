import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * My implementation of a dynamic array (ArrayList).
 */
public class DynamicArray<E> implements Iterable<E> {

    private static final int INIT_CAPACITY = 16;

    private Object[] elements;
    private int size;

    public DynamicArray() {
        this(INIT_CAPACITY);
    }

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
    @SuppressWarnings("unchecked")
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            E item = (E) o;
            if (item.equals(elements[i])) {
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
     * @return the element previously at positoin {@code index}
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
            sb.append(elements[i].toString())
                .append(",");
        }

        if (size > 0) {
            sb.append(elements[size - 1].toString());
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

                return (E) elements[index];
            }
        };
    }
}