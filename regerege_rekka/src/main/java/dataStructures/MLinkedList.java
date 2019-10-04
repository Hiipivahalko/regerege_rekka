package dataStructures;

public class MLinkedList<T> {

    private LinkNode<T> last;
    private LinkNode<T> first;
    private int size;

    public MLinkedList() {
        this.last = new LinkNode<T>(null, null, null);
        this.first = last;
        this.size = 0;
    }

    public void addLast(T key) {
        LinkNode<T> fresh = new LinkNode<T>(key, null, last);
        if (last.getKey() != null) last.setNext(fresh);
        last = fresh;
        if (first == null) first = fresh;
        size++;
    }

    public void addFirst(T key) {
        LinkNode<T> fresh = new LinkNode<T>(key, first, null);
        if (first.getKey() != null) first.setPrev(fresh);
        first = fresh;
        if (last.getKey() == null) last = fresh;
        size++;
    }

    public T popLast() {
        if (last.getKey() == null) {
            System.out.println("Error with popLast method MLinkedList<" + first.getClass()
                    + ">, Object was NULL");
            System.exit(1);
        }

        T value = last.getKey();
        last = last.getPrev();
        last.setNext(new LinkNode<T>(null, null));
        size--;
        return value;
    }

    public T popFirst() {
        if (first == null) {
            System.out.println("Error with popFirst method at MLinkedList<" + first.getClass()
                    + ">, Object was NULL");
            System.exit(1);
        }

        T value = first.getKey();
        first = first.getNext();
        first.setPrev(new LinkNode<T>(null, null));
        size--;
        return value;
    }

    public T peekFirst() {
        return first.getKey();
    }

    public T peekLast() {
        return last.getKey();
    }

    public int size() {
        return size;
    }
}
