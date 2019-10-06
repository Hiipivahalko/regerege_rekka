package dataStructures;

public class MLinkedList<T> {

    private LinkNode<T> last;
    private LinkNode<T> first;
    private LinkNode<T> empty;
    private int size;


    /**
     * Luodaan kahteensuuntaan linkitetty lista
     */
    public MLinkedList() {
        this.empty = new LinkNode<T>(null, null, null);
        this.last = empty;
        this.first = last;
        this.size = 0;
    }

    /**
     * Lisää listan loppuun uuden alkon
     * @param key - listaan lisättävä objekti
     */
    public void addLast(T key) {
        LinkNode<T> fresh = new LinkNode<T>(key, empty, empty);

        if (last.getKey() != null) {
            last.setNext(fresh);
            fresh.setPrev(last);
        }
        if (first.getKey() == null) first = fresh;
        else if (first.getKey() != null && size == 1) first = last;
        last = fresh;
        size++;
    }

    /**
     * Lisää listan alkuun uuden alkion
     * @param key - listaan lisättävä objekti
     */
    public void addFirst(T key) {
        LinkNode<T> fresh = new LinkNode<T>(key, empty, empty);

        if (first.getKey() != null) {
            first.setPrev(fresh);
            fresh.setNext(first);
        }

        if (last.getKey() == null) last = fresh;
        else if (last.getKey() != null && size == 1) last = first;
        first = fresh;
        size++;
    }

    /**
     * Poistaa listan viimeisen alkion
     * @return - metodi palauttaa poistetun alkion
     */
    public T popLast() {
        if (last.getKey() == null) {
            System.out.println("Error with popLast method MLinkedList<" + first.getClass()
                    + ">, Object was NULL");
            System.exit(1);
        }

        T value = last.getKey();
        last = last.getPrev();
        last.setNext(empty);
        size--;

        if (size <= 1) first = last;

        return value;
    }

    /**
     * Poistaa listan ensimmäisen alkon
     * @return - metodi palauttaa poistetun alkion
     */
    public T popFirst() {
        if (first == null) {
            System.out.println("Error with popFirst method at MLinkedList<" + first.getClass()
                    + ">, Object was NULL");
            System.exit(1);
        }

        T value = first.getKey();
        first = first.getNext();
        first.setPrev(empty);
        size--;

        if (size <= 1) last = first;
        return value;
    }

    /**
     * Palauttaa listan ensimmäisen alkion
     * @return
     */
    public T getFirst() {
        return first.getKey();
    }

    /**
     * Palauttaa listan viimeisen alkon
     * @return
     */
    public T getLast() {
        return last.getKey();
    }

    /**
     * Kertoo listan alioiden lukumäärän
     * @return
     */
    public int size() {
        return size;
    }
}
