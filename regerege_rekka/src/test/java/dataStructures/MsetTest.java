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
}