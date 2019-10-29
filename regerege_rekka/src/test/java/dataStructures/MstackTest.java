package dataStructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MstackTest {

    private Mstack<Integer> s;
    private Mstack<String> s2;

    @Before
    public void setUp() {
        this.s = new Mstack<>();
        this.s2 = new Mstack<>();
    }

    @Test
    public void push() {

        int num = 1;
        s.push(num);
        assertTrue(s.size() == 1);
        int i = s.pop();
        assertTrue(s.size() != 1);
        assertTrue(i == num);

        s.push(num);
        s.push(num);
        s.push(num);
        s.push(num);

        assertTrue(s.size() == 4);
    }

    @Test
    public void push2() {

        for (int i = 0; i < 150; i++) {
            s.push(1);
        }

        assertTrue(s.size() == 150);

    }

    @Test
    public void pushAndPopWithString() {

        String myString = "test";
        String myString2 = "Stack is best";
        s2.push(myString);
        assertTrue(s2.size() == 1);
        String ans = s2.pop();
        assertEquals("test", ans);

        s2.push(myString);
        s2.push(myString2);
        s2.push(myString);
        s2.push(myString2);

        assertTrue(s2.size() == 4);
        ans = s2.pop();
        assertTrue(s2.size() != 4);
        assertEquals("Stack is best", ans);
        ans = s2.pop();
        assertEquals("test", ans);
        ans = s2.peek();
        assertEquals("Stack is best", ans);

    }

    @Test
    public void pop() {

        int num = 1;
        s.push(2);
        s.push(3);
        s.push(5);
        s.push(6);
        s.push(8);

        assertTrue(s.size() == 5);

        int ret = s.pop();
        assertTrue(s.size()==4);
        assertTrue(ret == 8);
        ret = s.pop();
        assertTrue(s.size()==3);
        assertTrue(ret==6);

        s.pop();
        s.pop();
        s.pop();

        assertTrue(s.size()== 0);

    }

    @Test
    public void isEmpty() {

        assertTrue(s.empty());
        s.push(1);
        assertTrue(!s.empty());
        s.push(1);
        assertTrue(!s.empty());
        s.pop();
        assertTrue(!s.empty());
        s.pop();
        assertTrue(s.empty());

    }


}