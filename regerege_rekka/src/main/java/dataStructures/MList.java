package dataStructures;


import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class MList<T>  implements Iterable<T>{

    //private T[] objects;
    private Object[] objects;
    private int INITIALIZE_SIZE = 100;
    private int size;

    public MList() {
        this.objects = new Object[INITIALIZE_SIZE];
        this.size = -1;
    }

    /*public <T> T[] returnArray() {
        return new T[INITIALIZE_SIZE];
    }*/

    /**
     * Lisää listaan uuden alkion
     * @param t - lisättävä alkio
     */
    public void add(T t) {
        if (size + 1 == objects.length) {
            doubleArrayLength();
        }
        //System.out.println(t);
        objects[++size] = t;
    }

    /**
     * Palauttaa listan alkion kohdassa k
     * @param k - listan indeksi
     * @return - palauttaa listan alkion, jos indeksi k on listan välillä.
     */
    public T get(int k) {
        if (k >= size()) {
            System.out.println("Error: tried to get elements out of the range from MList");
            System.out.println("range was: [" + 0 + "-" + (size-1) + "]");
            System.out.println("Tried to get element of " + k);
            System.exit(1);
        }
        T val = (T) objects[k];

        return val;
    }

    /**
     * Poistaa listasta kohdassa k olevan alkion
     * @param k
     */
    public void remove(int k) {
        if (k >= size()) {
            System.out.println("Error: tried to get elements out of the range from MList");
            System.out.println("range was: [" + 0 + "-" + (size-1) + "]");
            System.out.println("Tried to get element of " + k);
            System.exit(1);
        }
        for (int i = k+1; i < size(); i++) {
            objects[i-1] = objects[i];
        }
        size--;

    }

    /**
     * Kertoo kuinka monta alkiota listassa on
     * @return - palauttaa listan alkioiden määrän
     */
    public int size() {
        return size+1;
    }

    /**
     * Suurentaa listan kokoa tuplaksi.
     * Ei lisää alkioita, mutta lisää mahdollisia indeksien määrää.
     */
    public void doubleArrayLength() {
        Object[] arr = new Object[objects.length*2];
        for (int i = 0; i < size(); i++) {
            arr[i] = objects[i];
        }
        objects = arr;
    }

    /**
     * Kasvattaa listan indeksien määrän k:ksi
     * @param k
     */
    public void increaseArrayLength(int k) {
        if (k < objects.length) return;
        Object[] arr = new Object[k];
        for (int i = 0; i < size(); i++) {
            arr[i] = objects[i];
        }
        objects = arr;

    }

    /**
     * Mahdollistaa MList objektin iteroinnin (esim forEach tyyliin)
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                return (T) objects[index++];
            }
        };
        return it;
    }
}
