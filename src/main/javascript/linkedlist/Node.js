/**
 * Class that represents information with a single element in a singly linked list.
 */
export class Node {

    /**
     * Data for this node.
     */
    item;

    /**
     * Pointer to next node.
     */
    next;

    /**
     * Creates a Node with the specified item and next values. 
     * Next is null by default.
     * 
     * @param {*} item data
     * @param {*} [next] pointer to next node 
     */
    constructor(item, next = null) {
        this.item = item;
        this.next = next;
    }
}