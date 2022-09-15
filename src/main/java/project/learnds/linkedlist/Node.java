package project.learnds.linkedlist;

/**
 * Class that represents information with a single element in a singly linked list.
 */
public class Node<E> {
    
    /**
     * Data for this node.
     */
    E item;

    /**
     * Pointer to next node.
     */
    Node<E> next;

    /**
     * Default constructor. Sets {@code next} pointer to null.
     * 
     * @param item data
     */
    public Node(E item) {
        this.item = item;
        next = null;
    }
}
