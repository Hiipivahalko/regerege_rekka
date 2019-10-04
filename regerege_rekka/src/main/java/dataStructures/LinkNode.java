package dataStructures;

public class LinkNode<E> {

    private E key;
    private LinkNode<E> next;
    private LinkNode<E> prev;

    public LinkNode(E key, LinkNode<E> prev) {
        this.key = key;
        this.next = prev;
    }

    public LinkNode(E key, LinkNode<E> next, LinkNode<E> prev) {
        this.key = key;
        this.next = next;
        this.prev = prev;
    }

    public E getKey() {
        return key;
    }

    public LinkNode<E> getNext() {
        return next;
    }

    public LinkNode<E> getPrev() {
        return prev;
    }

    public void setKey(E key) {
        this.key = key;
    }

    public void setNext(LinkNode<E> next) {
        this.next = next;
    }

    public void setPrev(LinkNode<E> prev) {
        this.prev = prev;
    }










}
