/**
 * Class that represents information with a single element in a doubly linked list.
 */
class Node {

    /**
     * Data for this node.
     */
    item;

    /**
     * Pointer to next node.
     */
    next;

    /**
     * Pointer to previous node.
     */
    prev;

    /**
     * Creates a Node with the specified item and next values. 
     * Next is null by default.
     * 
     * @param {*} item data
     * @param {*} [next] pointer to next node 
     * @param {*} [prev] pointer to previous node
     */
    constructor(item, next = null, prev = null) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
}

/**
 * Class representing a doubly linked list. Allows for null values.
 */
class DoublyLinkedList {

    /**
     * Dummyhead node. Eliminates having to check for null values
     * when adding or removing the head.
     */
    #dhead;

    /**
     * Dummy tail node. Eliminates having to check for null values
     * when adding or removing the tail.
     */
    #dtail;

    /**
     * Number of elements in this list.
     */
    #size;

    /**
     * Create a DoublyLinkedList.
     */
    constructor() {
        this.#dhead = new Node(null);
        this.#dtail = new Node(null);
        this.#dhead.next = this.#dtail;
        this.#dtail.prev = this.#dhead;

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
        this.#dtail.prev.next = newNode;
        newNode.prev = this.#dtail.prev;
        newNode.next = this.#dtail;
        this.#dtail.prev = newNode;

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

        if (index === (this.#size - 1)) {
            return this.#dtail.prev.item;
        }

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

            curr = curr.next;
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
        for (let i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        curr.next = null;
        curr.prev = null;

        this.#size--;

        return curr.item;
    }

    /**
     * Returns a string representation of a SinglyLinkedList
     * 
     * @returns {string} string representation of a SinglyLinkedList
     */
    toString() {
        let str = "";

        str += "[";

        let curr = this.#dhead.next;
        while (curr !== this.#dtail && curr.next !== this.#dtail) {
            if (curr.item === null) {
                str += "null";
            } else {
                str += curr.item.toString();
            }

            str += " <-> ";

            curr = curr.next;
        }

        if (curr != this.#dtail) {
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
     * @throws Will throw an error if index is out of range
     */
    #checkBounds(index) {
        if (index < 0 || index >= this.#size) {
            throw `index = ${index} is out of range!`;
        }
    }
}

/**
 * Simple testing function.
 */
 function testDLL() {
    const list = new DoublyLinkedList();
    console.log(`Original: ${list}\n`);
    
    list.add(1);
    list.add(2);
    list.add(3);
    
    console.log(`After adding: ${list}`);
    console.log(`Size: ${list.size}\n`);
    
    console.log("Testing getAt()");
    for (let i = 0; i < list.size; i++) {
        console.log(`i: ${i} -> ${list.getAt(i)}`);
    }

    console.log();

    console.log("Test indexOf()");
    console.log(`${list.indexOf(1)}`);
    console.log(`${list.indexOf(2)}`);
    console.log(`${list.indexOf(3)}\n`);

    console.log("Tesing remove()");
    let x = list.remove(0);
    console.log(`${x} - ${list}`);

    list.add(4);
    x = list.remove(1);
    console.log(`${x} - ${list}`);

    list.add(5);
    x = list.remove(2);
    console.log(`${x} - ${list}`);

    list.getAt(-1);
}

testDLL();