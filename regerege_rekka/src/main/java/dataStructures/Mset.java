package dataStructures;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Mset<T> implements Iterable<T> {

    private MList<LinkNode<T>>[] hashArray;
    private int size;
    private final int N =  1002257;
    private PriorityQueue<T> objects;

    /**
     * Uusi joukko olio
     */
    public Mset() {
        this.hashArray = new MList[this.N];
        this.size = 0;
        this.objects = new PriorityQueue<>();
    }

    public PriorityQueue<T> getObjects() {
        return objects;
    }

    /**
     * Laskee annetun objektin hajautustaulun arvon
     * @param hashcode - objektin oma hajautusarvo
     * @return - hajautustaulun indeksi
     */
    private int hashFunction(int hashcode) {
        return hashcode % N;
    }

    /**
     * Funktio kertoo miten iso joukon (Set) koko on
     * @return - joukon objektien määrän
     */
    public int size() {
        return size;
    }

    /**
     * Lisää joukkoon yhden uuden objektin.
     * Jos objekti on jo joukossa, sitä ei lisätä
     * @param t
     */
    public void add(T t) {
        int key = hashFunction(t.hashCode());
        LinkNode<T> nextNode = new LinkNode<>(t,null,null);

        if (hashArray[key] == null) {
            hashArray[key] = new MList<>();
            hashArray[key].add(nextNode);
            objects.add(t);
            size++;
        } else {
            // tarkistetaan onko objekti jo joukossa, ylivuoto listan avulla
            boolean already = false;
            for (LinkNode<T> ln : hashArray[key]) {
                if (ln.getKey() == t) {
                    already = true;
                    break;
                }
            }
            if (!already) {
                hashArray[key].add(nextNode);
                objects.add(t);
                size++;
            }
        }
    }

    /**
     * Joukon objektien iterointi keon avulla
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return objects.iterator();
    }

    /**
     * Tarkistaa onko kaksi joukkoa samat
     * @param o - verrattava joukko
     * @return - palauttaa true jos joukot sisältää samat objektit, muuten
     * false
     */
    @Override
    public boolean equals(Object o) {

        if (this.getClass() != o.getClass()) return false;

        Mset<T> s = (Mset<T>) o;

        if (this.size != s.size) return false;

        PriorityQueue<T> tempThis = objects;
        PriorityQueue<T> tempComp = s.getObjects();

        while (!tempThis.isEmpty()) {
            T t = tempThis.poll();
            T t2 = tempComp.poll();
            if (!t.equals(t2)) return false;
        }

        return true;
    }
}
