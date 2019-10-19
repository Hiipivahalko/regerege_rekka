package dataStructures;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MListTest {

    private MList<Integer> list;
    private Random random;

    @Before
    public void setUp() throws Exception {
        this.list = new MList<>();
        this.random = new Random(1000000);
    }

    /**
     * Testataan listaan listaan objektin lisäämistä
     */
    @Test
    public void add() {
        assertTrue(list.size() == 0);

        list.add(1);
        assertTrue(list.size() == 1);

        list.add(3);
        list.add(6);

        assertTrue(list.size() == 3);

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        assertTrue(list.size() == 13);
    }

    /**
     * Testataan listan kohdasta k olevan objektin oikeellisuus
     */
    @Test
    public void get() {

        assertTrue(list.size() == 0);

        list.add(3);

        assertTrue(list.get(0) == 3);

        list.add(4);
        list.add(5);
        list.add(6);

        assertTrue(list.get(0) == 3);
        assertTrue(list.get(1) == 4);
        assertTrue(list.get(2) == 5);
        assertTrue(list.get(3) != 1);
    }

    /**
     * Testataan get-funktiota siten että laitetaan listaan random lukuja
     * ja tarkastetaan että indeksissä 50 on oikea luku
     */
    @Test
    public void get2() {
        int num = 0;

        for (int i = 0; i < 100; i++) {
            int next = random.nextInt();
            if (i == 50) num = next;
            list.add(next);
        }
        //System.out.println();
        assertTrue(list.get(50) == num);
    }

    /**
     * Testataa listasta objektin poistoa
     */
    @Test
    public void remove() {
        assertTrue(list.size() == 0);

        list.add(4);
        list.add(2);
        list.add(7);

        assertTrue(list.size() == 3);
        assertTrue(list.get(0) == 4);

        list.remove(0);
        assertTrue(list.size() == 2);
        assertTrue(list.get(0) == 2);

        assertTrue(list.get(1) == 7);

        list.remove(1);
        assertTrue(list.size() == 1);
        assertTrue(list.get(0) == 2);
    }

    /**
     * Toinen testi listan objektien poistoon, siten että poistaa listan keskeltä kaksi
     * objektia, jolloin keskeltä poistuu kaksi objektia ja listan "häntä" siirtyy oikeaan indeksiin.
     */
    @Test
    public void remove2() {
        assertTrue(list.size() == 0);

        list.add(4);
        list.add(2);
        list.add(7);
        list.add(5);

        list.remove(1);
        list.remove(1);

        assertTrue(list.size() == 2);
        assertTrue(list.get(1) == 5);
    }

    /**
     * Testataan listan indeksien tuplaamista
     */
    @Test
    public void doubleArrayLength() {
        assertTrue(list.size() == 0);

        for (int i = 0; i < 150; i++) {
            list.add(i);
        }

        assertTrue(list.size() == 150);
    }

    /**
     * Testaan listan indeksien kasvattamista manuaalisesti
     */
    @Test
    public void increaseArrayLength() {
        assertTrue(list.size() == 0);

        list.increaseArrayLength(150);
        assertTrue(list.size() == 0);

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        assertTrue(list.size() == 100);
        assertTrue(list.get(5) == 5);

        list.increaseArrayLength(1000);
        assertTrue(list.size() == 100);
    }

    /**
     * Testaan että listan iterointi etenee odotetulla tavalla
     * eli listan alusta loppuun
     */
    @Test
    public void iterator() {

        for (int i = 0; i< 50; i++) {
            list.add(i);
        }
        assertTrue(list.size() == 50);

        int idx = 0;
        for (int i : list) {
            assertTrue(list.get(i) == idx);
            idx++;
        }
    }
}