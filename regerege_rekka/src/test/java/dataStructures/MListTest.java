package dataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MListTest {

    private MList<Integer> list;

    @Before
    public void setUp() throws Exception {
        this.list = new MList<>();
    }

    @Test
    public void add() {
        assertTrue(list.size() == 0);

        list.add(1);
        assertTrue(list.size() == 1);

        list.add(3);
        list.add(6);

        assertTrue(list.size() == 3);
    }

    @Test
    public void get() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void size() {
    }

    @Test
    public void doubleArrayLength() {
    }

    @Test
    public void increaseArrayLength() {
    }

    @Test
    public void iterator() {
    }
}