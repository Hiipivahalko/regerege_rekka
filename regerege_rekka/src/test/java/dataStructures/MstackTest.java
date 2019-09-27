package dataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MstackTest {

    private Mstack<Integer> s;

    @Before
    public void setUp() {
        this.s = new Mstack<>();
    }

    @Test
    public void push() {

        int num = 1;
        s.push(num);
        assertTrue(s.size() == 1);
        int i = s.pop();
        assertTrue(i == num);

        s.push(num);
        s.push(num);
        s.push(num);
        s.push(num);

        assertTrue(s.size() == 4);


    }

    @Test
    public void pop() {

        int num = 1;
        s.push(2);
        s.push(3);
        s.push(5);
        s.push(6);
        s.push(1);

        assertTrue(s.size() == 5);

        int ret = s.pop();

        assertTrue(s.size()==4);
        assertTrue(ret == 1);
        ret = s.pop();
        assertTrue(s.size()==3);
        assertTrue(ret==6);

        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        assertTrue(s.size()== 0);

    }

    @Test
    public void isEmpty() {

        assertTrue(s.isEmpty());
        s.push(1);
        assertTrue(!s.isEmpty());
        s.push(1);
        assertTrue(!s.isEmpty());
        s.pop();
        assertTrue(!s.isEmpty());
        s.pop();
        assertTrue(s.isEmpty());

    }

    @Test
    public void push2() {

        for (int i = 0; i < 150; i++) {
            s.push(1);
        }

        assertTrue(s.size() == 150);

    }
}