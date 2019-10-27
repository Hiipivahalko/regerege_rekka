package dataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MsetTest {

    private Mset<Integer> mySet;

    @Before
    public void setUp() throws Exception {
        this.mySet = new Mset<>();
    }

    /**
     * Testataan joukkoon yhden objektin lisäämistä
     */
    @Test
    public void add() {
        assertTrue(mySet.size() == 0);

        mySet.add(1);
        assertTrue(mySet.size() == 1);

        for (int i : mySet) {
            //System.out.println("--------------------: " + i);
            assertTrue(i == 1);
        }
    }

    /**
     * Testaan monen alkion lisäämistä joukkoon
     */
    @Test
    public void add2() {
        assertTrue(mySet.size() == 0);

        mySet.add(1);
        mySet.add(3);
        mySet.add(5);

        assertTrue(mySet.size() == 3);
    }

    /**
     * Testataan että jos lisätään objekti joka on jo joukossa,
     * niin joukkoon ei tule uusia objekteja
     */
    @Test
    public void add3() {
        assertTrue(mySet.size() == 0);

        mySet.add(1);
        mySet.add(5);

        assertTrue(mySet.size() == 2);

        mySet.add(1);
        assertTrue(mySet.size() == 2);

        mySet.add(5);
        mySet.add(5);

        int j = 0;
        for (int i : mySet) {
            j++;
        }
        //System.out.println("------------------:" + j);
        assertTrue(j == mySet.size());

        assertTrue(mySet.size() == 2);

        mySet.add(6);
        assertTrue(mySet.size() == 3);
    }

    /**
     * Testataan ovatko kaksi joukkoa samat samoilla objekteilla
     */
    @Test
    public void equals() {
        Mset<Integer> s = new Mset<>();

        mySet.add(6);
        s.add(6);

        mySet.add(1);
        s.add(1);

        assertTrue(mySet.equals(s));
    }

    /**
     * Testataan ovatko kaksi eri joukkoa samat
     */
    @Test
    public void equals2() {
        Mset<Integer> s = new Mset<>();

        mySet.add(6);
        s.add(6);

        mySet.add(3);
        s.add(1);

        assertTrue(!mySet.equals(s));
    }

    /**
     * tarkistetaan joukon oma hasharvo
     */
    @Test
    public void testHashCode() {
        int hash = 40709;

        Mset<Character> chSet = new Mset<>();
        assertTrue(chSet.size() == 0);
        chSet.add('t');
        chSet.add('e');
        chSet.add('s');
        chSet.add('i');

        assertTrue(chSet.size() == 4);
        assertTrue(chSet.hashCode() == hash);
    }

    /**
     * Testataan onko joukossa tietty objekti jo valmiiksi
     */
    @Test
    public void contains() {
        assertTrue(mySet.size() == 0);

        mySet.add(5);
        boolean isIt = mySet.contains(5);
        boolean isIt2 = mySet.contains(10);

        assertTrue(isIt);
        assertTrue(!isIt2);
    }

    /**
     * Testataan että joukosta löytyy objektit jolla on samat hajautusarvot
     */
    @Test
    public void constains2() {
        Mset<String> sSet = new Mset<>();

        assertTrue(sSet.size() == 0);
        sSet.add("Ea");
        sSet.add("FB");

        assertTrue(sSet.contains("FB"));
        assertTrue(sSet.contains("Ea"));
    }
}