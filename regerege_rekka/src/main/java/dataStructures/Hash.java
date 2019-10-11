package dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

public class Hash<T> {

    private ArrayList<T>[] hashTable;
    private int N = 1002257; // modulo + size of hashTable

    public Hash() {
        this.hashTable = new ArrayList[N];
    }

    public int hashFunction(int hashCode) {
        return hashCode % N;
    }



}
