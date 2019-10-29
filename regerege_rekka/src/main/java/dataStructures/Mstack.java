package dataStructures;

public class Mstack<T> {

    private LinkNode<T> top;
    private int size;
    private LinkNode<T> empty;

    /**
     * Luo uuden pinon tietorakenteen
     */
    public Mstack() {
        this.empty = new LinkNode<>(null, null, null);
        this.top = empty;
        this.size = 0;
    }

    /**
     * Asettaa pinon ensimmäiseksi/päällimäiseksi alkioksi annetun arvon
     * @param t - pinoon asetettava arvo
     */
    public void push(T t) {

        LinkNode<T> fresh = new LinkNode<>(t, empty, empty);
        fresh.setPrev(top);
        top = fresh;
        size++;
    }

    /**
     * Poistaa pinosta päällimmäisen arvon ja asettaa päällimäiseksi uuden arvon
     * @return - palauttaa poistetun arvon
     */
    public T pop() {
        if (top.getKey() == null) {
            System.out.println("Error with pop method MStack<>, Object was NULL");
            System.out.println(size());
            System.exit(1);
        }
        T value = top.getKey();
        top = top.getPrev();
        size--;
        return value;
    }

    /**
     * Näyttää mikä arvo/alkio on pinon päällimmäisenä, mutta ei poista sitä pinosta
     * @return - palauttaa päällimmäisen alkon arvon
     */
    public T peek() {
        return (T) top.getKey();
    }

    /**
     * Kertoo onko pino tyhjä
     * @return - palauttaa TRUE jos pino on tyhjä, muuten FALSE
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Kertoo kuinka monta alkiota on pinossa
     * @return - alkioiden lukumäärä
     */
    public int size() {
        return size;
    }
}
