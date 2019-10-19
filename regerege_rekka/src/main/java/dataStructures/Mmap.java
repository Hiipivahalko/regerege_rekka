package dataStructures;

import java.util.ArrayList;

public class Mmap<T, K> {

    private ArrayList<T>[] hashTable;
    private int N = 1002257; // modulo + size of hashTable

    public Mmap() {
        this.hashTable = new ArrayList[N];
    }

    private int hashFunction(int hashCode) {
        return hashCode % N;
    }

    //public put(T, K)
}
