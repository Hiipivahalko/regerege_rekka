package dataStructures;

import java.util.Iterator;

public class Mset<T extends Comparable<T>> implements Iterable<T> {

    private MList<LinkNode<T>>[] hashArray;
    private int size;
    private final int N =  1002257;
    private MPriorityQueue<T> objects;

    /**
     * Uusi joukko olio
     */
    public Mset() {
        this.hashArray = new MList[this.N];
        this.size = 0;
        this.objects = new MPriorityQueue<>();
    }

    public MPriorityQueue<T> getObjects() {
        return objects;
    }

    /**
     * Laskee annetun objektin hajautustaulun arvon
     * @param hashcode - objektin oma hajautusarvo
     * @return - hajautustaulun indeksi
     */
    private int hashFunction(T hashcode) {
        return Math.abs(hashcode.hashCode()) % N;
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
        int key = hashFunction(t);
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
     * Tarkistetaan onko kysytty objekti joukossa
     * @param key - kysytty objekti
     * @return - true jos on, muuten false
     */
    public boolean contains(T key) {

        int hash = hashFunction(key);

        if (hashArray[hash] != null) {
            if (hashArray[hash].size() == 1 && hashArray[hash].get(0).getKey() == key) {
                return true;
            } else {
                for (LinkNode<T> ln : hashArray[hash]) {
                    if (ln.getKey() == key) return true;
                }
            }
        }

        return false;
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

        MPriorityQueue<T> tempThis = objects;
        MPriorityQueue<T> tempComp = s.getObjects();

        while (!tempThis.isEmpty()) {
            T t = tempThis.poll();
            T t2 = tempComp.poll();
            if (!t.equals(t2)) return false;
        }

        return true;
    }

    /**
     * Kertoo joukon oman hajautusarvon
     * @return
     */
    @Override
    public int hashCode() {
        Iterator it = objects.iterator();
        int hash = 0;
        int a = 7;
        while (it.hasNext()) {
            hash = (hash * a + (int) it.next().hashCode()) % N;
        }
        return hash;
    }
}
