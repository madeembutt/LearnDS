import { Node } from "./Node.js";

/**
 * Class representing a singly linked list. Allows for null values.
 */
class SinglyLinkedList {

    /**
     * Dummy head node. Eliminates having to check for null values
     * when adding or removing the head.
     */
    #dhead;

    /**
     * Tail node.
     */
    #tail;

    /**
     * Number of elements in this list.
     */
    #size;

    /**
     * Create a SinglyLinkedList.
     */
    constructor() {
        this.#dhead = new Node(null);
        this.#tail = this.#dhead;
        this.#size = 0;
    }

    /**
     * Appends the specified element e to the end of the list.
     * 
     * @param {*} e element to append to this list
     * @returns {boolean} true
     */
    add(e) {
        const newNode = new Node(e);
        this.#tail.next = newNode;
        this.#tail = newNode;

        this.#size++;

        return true;
    }

    /**
     * Returns the element at position index.
     * 
     * @param {number} index index of the element to return
     * @returns {*} element at position index
     * @throws Will throw an error if the index is ouf of range
     */
    getAt(index) {
        this.#checkBounds(index);

        let curr = this.#dhead.next;
        for (let i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.item;
    }

    /**
     * Returns the index of the first occurrence of e or -1 if this list doesn't contain it.
     * 
     * @param {*} e element to search for
     * @returns {number} index of the irst occurrence of e or -1 if this list doesn't contain i
     */
    indexOf(e) {
        if (this.isEmpty()) {
            return -1;
        }

        let curr = this.#dhead.next;
        for (let i = 0; i < this.#size; i++) {
            if ((e === null && curr.item === null) || (e !== null && e === curr.item)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Removes the element at position index.
     * Returns the element that was removed.
     * 
     * @param {number} index index of the element to remove
     * @returns {*} the element previously at position index
     * @throws Will throw an error if the index is ouf of range
     */
    remove(index) {
        this.#checkBounds(index);

        let curr = this.#dhead.next;
        for (let i = -1; i < (index - 1); i++) {
            curr = curr.next;
        }

        const retVal = curr.next;
        curr.next = curr.next.next;

        if (retVal === this.#tail) {
            this.#tail = curr;
        }

        this.#size--;

        return retVal.item;
    }

    /**
     * Returns a string representation of a SinglyLinkedList
     * 
     * @returns {string} string representation of a SinglyLinkedList
     */
    toString() {
        //const capacity = !this.isEmpty() ? 2 + (2 * (this.#size - 1)) + 1 : 2;
        let str = "";

        str += "[";

        let curr = this.#dhead.next;
        while (curr !== null && curr.next !== null) {
            if (curr.item === null) {
                str += "null";
            } else {
                str += curr.item.toString();
            }

            str += " -> ";

            curr = curr.next;
        }

        if (curr != null) {
            if (curr.item === null) {
                str += "null";
            } else {
                str += curr.item.toString();
            }
        }

        str += "]";

        return str;
    }

    /**
     * Returns the number of elements in this list.
     * 
     * @return {number} Returns the number of elements in this list
     */
    get size() {
        return this.#size;
    }

    /**
     * Returns true if this list contains no elements.
     * 
     * @returns {boolean} true if this list contains no elements 
     */
    isEmpty() {
        return this.#size <= 0;
    }

    /**
     * Checks if index is out of range.
     * 
     * @param {number} index index to check
     * @throws Will throw and error if index is out of range
     */
    #checkBounds(index) {
        if (index < 0 || index >= this.#size) {
            throw `index = ${index} is out of range!`;
        }
    }
}

let list = new SinglyLinkedList();
console.log(`${list}`);

list.add(1);
list.add(2);
list.add(3);

console.log(`${list}`);
console.log(list.size);