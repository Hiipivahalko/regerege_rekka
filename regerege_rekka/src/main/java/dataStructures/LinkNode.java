package dataStructures;

public class LinkNode<T> {

    private T key;
    private LinkNode<T> next;
    private LinkNode<T> prev;

    /**
     * Luo uuden linkkisolmun, jolla on tieto seuraavasta ja edellisestä saman tyyppisestä oliosta.
     * Toimii apuoliona esim. linkitetylle listalle ja pinolle.
     * @param key
     * @param prev
     */
    public LinkNode(T key, LinkNode<T> prev, LinkNode<T> next) {
        this.key = key;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Palauttaa linkkisolmun arvon
     * @return
     */
    public T getKey() {
        return key;
    }

    /**
     *
     * @return - Palauttaa seuraavaksi osoitetun linkkisolmun
     */
    public LinkNode<T> getNext() {
        return next;
    }

    /**
     *
     * @return - palauttaa edelliseksi osoitetun linkkisolmun
     */
    public LinkNode<T> getPrev() {
        return prev;
    }

    /**
     * Asettaa linkkisolmulle uuden arvon
     * @param key - uusi arvo
     */
    public void setKey(T key) {
        this.key = key;
    }

    /**
     * Asettaa linkkisolmulle uuden seuraajasolmun
     * @param next - seuraavaksi osoitettu solmu
     */
    public void setNext(LinkNode<T> next) {
        this.next = next;
    }

    /**
     * Asettaa linkkisolmulle uuden edeltäjäsolmun
     * @param prev - edelliseksi osoitettu solmu
     */
    public void setPrev(LinkNode<T> prev) {
        this.prev = prev;
    }










}
