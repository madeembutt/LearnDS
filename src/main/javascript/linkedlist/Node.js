export class Node {

    item;
    next;

    constructor(item, next = null) {
        this.item = item;
        this.next = next;
    }
}