package dataStructures;

public class Mstack<E> {

    private LinkNode<E> top;
    private int size;


    public Mstack() {
        this.top = null;
        this.size = 0;
    }

    public void push(Object t) {

        LinkNode<E> fresh = new LinkNode<>((E) t, top);
        top = fresh;
        size++;
    }

    public E pop() {
        if (top == null) {
            System.out.println("Error with pop method MStack<>, Object was NULL");
            System.out.println(size());
            System.exit(1);
        }
        Object value = top.getKey();
        System.out.println("value:" + value);
        top = top.getPrev();
        size--;
        return (E) value;
    }

    public E peek() {
        return top.getKey();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
