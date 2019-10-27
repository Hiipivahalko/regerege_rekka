package dataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MmapTest {

    private Mmap<String, Integer> myMap;

    @Before
    public void setUp() throws Exception {
        this.myMap = new Mmap<>();
    }

    /**
     * Testataan hajautustauluun arvo-parien laittamista
     */
    @Test
    public void put() {
        assertTrue(myMap.size() == 0);
        myMap.put("testi", 5);
        assertTrue(myMap.size() == 1);
        myMap.put("jee", 9);
        myMap.put("tiralabra", 5);
        assertTrue(myMap.size() == 3);
    }

    /**
     * Testataan että saman arvon laittamista hajautustauluun ei laita sitä useaan kertaa
     */
    @Test
    public void put2() {
        assertTrue(myMap.size() == 0);
        myMap.put("testi",10);
        assertTrue(myMap.size() == 1);
        myMap.put("testi", 10);
        assertTrue(myMap.size() == 1);
    }

    /**
     * Laitetaa hajautustauluun kaksi saman hajautusarvon omaavaa objektia,
     * tarkistetaan että hajautustauluun menee kaksi avain-arvo paria
     */
    @Test
    public void put3() {
        Mmap<String, Integer> sMap = new Mmap<>();
        assertTrue(sMap.size() == 0);

        sMap.put("Ea", 70);
        sMap.put("FB", 55);

        assertTrue(sMap.size() == 2);

        sMap.put("FB",30);
        assertTrue(sMap.size() == 2);
    }

    /**
     * Testataan että saadaan hajautustaulusta avaimella haluutu arvo
     */
    @Test
    public void get() {
        assertTrue(myMap.size() == 0);

        myMap.put("testi", 500);
        int value = myMap.get("testi");
        assertTrue(value == 500);
    }

    /**
     * Sama kuin edellinen get-testi, mutta testataan usella avain-arvo parilla
     */
    public void get2() {
        assertTrue(myMap.size() == 0);

        myMap.put("test", 70);
        myMap.put("tiralabra", 5);

        assertTrue(myMap.size() == 2);
        assertTrue(myMap.get("test") == 70);
        assertTrue(myMap.get("tiralabra") == 5);
        assertTrue(myMap.size() == 2);

        myMap.put("surf", 1000);
        assertTrue(myMap.size() == 3);
    }

    /**
     * yritetään hakea hajautustaulusta avainta mitä siellä ei ole
     */
    @Test
    public void get3() {
        assertTrue(myMap.size() == 0);

        myMap.put("testi", 400);
        myMap.put("green wawe", 55);

        assertTrue(myMap.get("padel") == null);
    }

    /**
     * asetetaan hajautustauluun kaksi saman hajautusarvon avainta,
     * testataan etta saadaa oikea arvo silti vaikka törmäys sattuu
     */
    @Test
    public void get4() {
        Mmap<String, Integer> sMap = new Mmap<>();
        assertTrue(sMap.size() == 0);

        sMap.put("Ea", 5);
        sMap.put("FB", 10);

        int val = sMap.get("Ea");
        int val2 = sMap.get("FB");

        assertTrue(val != val2);
        assertTrue(sMap.size() == 2);

        assertTrue(val == 5);
        assertTrue(val2 == 10);

    }

    /**
     * Testataan että löytyykö hajautustaulusta tiettyä avainta jo valmiiksi
     */
    @Test
    public void containsKey() {

        assertTrue(myMap.size() == 0);

        myMap.put("test", 600);

        assertTrue(myMap.containsKey("test"));
        assertTrue(!myMap.containsKey("joojoo"));
    }
}