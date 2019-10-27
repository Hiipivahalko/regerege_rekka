package dataStructures;

import java.util.Iterator;

public class MPriorityQueue<T extends Comparable<T>>  implements Iterable<T> {

    private MList<T> objects;

    public MPriorityQueue() {
        this.objects = new MList<>();
        this.objects.add(null); // lisätään tyhjä solmu kekooon, jotta keon toiminnot toimivat helmpommin
    }

    /**
     * kertoo kekotaulukon suuruuden
     * @return
     */
    public int size() {
        return objects.size()-1;
    }

    /**
     * Kertoo solmun vanhemman indeksin kekotaulukossa
     * @param i - lapsisolmun indeksi
     * @return - vanhemman indeksi
     */
    public int parent(int i) {
        return i / 2;
    }

    /**
     * Kertoo solmun vasemmanlapsen indeksin kekotaulukossa
     * @param i - vanhenpisolmu
     * @return - vasemmanlapsen indeksin
     */
    public int rightChild(int i) {
        return (i*2) +1;
    }

    /**
     * Kertoo solmun oikeanlapsen indeksin kekotaulukossa
     * @param i - vanhempisolmu
     * @return - oikeanlapsen indeksin
     */
    public int leftChild(int i) {
        return (i*2);
    }

    /**
     * Lisätään kekoon uusi solmu
     * @param node - lisättävä solmu
     */
    public void add(T node) {
        objects.add(node);
        int i = size();
        while (i > 1 && objects.get(parent(i)).compareTo(node) > 0) {
            objects.set(i, objects.get(parent(i)));
            i = parent(i);
        }
        objects.set(i, node);
    }

    /**
     * Poistaa keon pienemmän solmun
     * @return - pienin solmu
     */
    public T poll() {
        T max = objects.get(1);
        objects.set(1, objects.get(size()));
        objects.remove(size());
        heapify(1);
        return max;
    }

    /**
     * Tasapainottaa kekotaulukon keon ominaisuuksien mukaiseksi
     * @param i
     */
    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);

        if (right <= size()) {
            int smaller = 0;
            if (objects.get(left).compareTo(objects.get(right)) < 0) smaller = left;
            else smaller = right;

            if (objects.get(i).compareTo(objects.get(smaller)) > 0) {
                T temp = objects.get(smaller);
                objects.set(smaller, objects.get(i));
                objects.set(i, temp);
                heapify(smaller);
            }
        } else if (left == size() && objects.get(i).compareTo(objects.get(left)) > 0) {
            T temp = objects.get(left);
            objects.set(left, objects.get(i));
            objects.set(i, temp);
        }
    }


    /**
     * Kertoo onko keko tyhjä
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Iteroi kekotaulukon läpi, ei välttämättä halutussa suuruusjärjestyksessä
     * johtuen keon ominaisuukista
     * @return
     */
    @Override
    public Iterator<T> iterator() {

        Iterator<T> it = new Iterator<T>() {
            private int idx = 1;

            @Override
            public boolean hasNext() {
                return idx < size()+1;
            }

            @Override
            public T next() {
                return (T) objects.get(idx++);
            }
        };
        return it;
    }
}
