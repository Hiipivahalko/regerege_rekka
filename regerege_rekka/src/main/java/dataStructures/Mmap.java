package dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

public class Mmap<K extends Comparable<K>, V extends Comparable<V>> {

    private MList<LinkNode<Pair<K,V>>>[] hashTable;
    private Mset<K> keySet;
    private Mset<V> valueSet;
    private int N = 1002257; // modulo + size of hashTable
    private int size;

    public Mmap() {
        this.hashTable = new MList[N];
        this.keySet = new Mset<>();
        this.valueSet = new Mset<>();
        this.size = 0;
    }

    private int hashFunction(K hashCode) {
        return Math.abs(hashCode.hashCode()) % N;
    }

    /**
     * Lisää hajautustauluun uuden parin
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int hashKey = hashFunction(key);
        Pair<K,V> nextPair = new Pair<>(key, value);
        LinkNode<Pair<K,V>> nextNode = new LinkNode<>(nextPair,null,null);

        if (hashTable[hashKey] == null) {
            hashTable[hashKey] = new MList<>();
            hashTable[hashKey].add(nextNode);
            keySet.add(key);
            valueSet.add(value);
            size++;
        } else {
            boolean already = false;

            for (LinkNode<Pair<K,V>> ln: hashTable[hashKey]) {
                if (ln.getKey().getKey() == key) {
                    already = true;
                    break;
                }
            }
            if (!already) {
                hashTable[hashKey].add(nextNode);
                keySet.add(key);
                valueSet.add(value);
                size++;
            }
            // Ei tarvitse implementoida päivitystä hajautustaulun tietylle avaimelle
        }
    }

    /**
     * Palauttaa arvon joka on tallennettu hajautus tauluun tietylle avaimelle
     * @param key - hajautustaulun avain
     * @return - jos annetulla avaimella arvo, niin palauttaa arvon V, muuten null
     */
    public V get(K key) {
        V value;
        int hashKey = hashFunction(key);
        if (hashTable[hashKey] == null) return null;
        else if (hashTable[hashKey].size() == 1) return hashTable[hashKey]
                .get(0).getKey().getValue();
        else {
            for (LinkNode<Pair<K,V>> ln : hashTable[hashKey]) {
                if (ln.getKey().getKey() == key) return ln.getKey().getValue();
            }
        }
        return null;
    }

    /**
     * Tarkistaa löytyykö hajautustaulusta annettua avainta
     * @param key - etsittävä avain
     * @return - true jos löytyy hajautustaulun avaimista, muuten false;
     */
    public boolean containsKey(K key) {
        int hashKey = hashFunction(key);

        if (hashTable[hashKey] != null && keySet.contains(key)) {
            return true;
        }


        return false;
    }

    /**
     * Hajautustaulun avaimien iterointi
     * @return
     */
    public Iterator<K> keySetIterate() {
        return keySet.iterator();
    }

    /**
     * Hajautustaulun arvojen iterointi
     * @return
     */

    public Iterator<V> valueSetIterate() {
        return valueSet.iterator();
    }

    /**
     * Kertoo kuinka monta avain-arvo paria hajautustauluun on tallennettu
     * @return - avain-arvo parien määrä
     */
    public int size() {
        return size;
    }


}
