package dataStructures;

import java.util.Iterator;

public class MLinkedList<T> implements Iterable<T> {

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
            throw new NullPointerException();
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
        if (first.getKey() == null) {
            throw new NullPointerException();

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
    public T getFirstKey() {
        return first.getKey();
    }

    /**
     * Palauttaa listan viimeisen alkon
     * @return
     */
    public T getLastKey() {
        return last.getKey();
    }

    /**
     * Kertoo listan alioiden lukumäärän
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Lisätään toinen linkitettylistan objektit listan loppuun
     * @param toCopy - kopioitava linkitettylista
     */
    public void addAll(MLinkedList<T> toCopy) {
        Iterator it = toCopy.iterator();

        while (it.hasNext()) {
            addLast((T) it.next());
        }
    }

    /**
     * linkitetynlistan iterointi ensimmäisestä objektista viimeiseen
     * @return
     */
    @Override
    public Iterator<T> iterator() {

        Iterator<T> it = new Iterator<T>() {

            LinkNode<T> curr = first;
            @Override
            public boolean hasNext() {
                if (curr.getNext() == null) return false;
                return true;
            }

            @Override
            public T next() {
                T value = curr.getKey();
                curr = curr.getNext();
                return value;
            }
        };
        return it;
    }
}
