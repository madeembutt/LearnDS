import { Node } from "./Node.js";

class SinglyLinkedList {

    #dhead;
    #tail;

    #size;

    constructor() {
        this.#dhead = new Node(null);
        this.#tail = this.#dhead;
        this.#size = 0;
    }

    add(e) {
        const newNode = new Node(e);
        this.#tail.next = newNode;
        this.#tail = newNode;

        this.#size++;

        return true;
    }

    getAt(index) {
        this.#checkBounds(index);

        let curr = this.#dhead.next;
        for (let i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.item;
    }

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

    get size() {
        return this.#size;
    }

    isEmpty() {
        return this.#size <= 0;
    }

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