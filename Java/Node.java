/**
 * Class that represents information with a single element in a linked list.
 */
public class Node<E> {
    E item;
    Node<E> next;

    /**
     * Default constructor. Sets next pointer to null.
     * 
     * @param item data
     */
    public Node(E item) {
        this(item, null);
    }

    /**
     * Constructor that uses the specified {@code item} and {@code next} values.
     * 
     * @param item data
     * @param next pointer to next node
     */
    public Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }
}
