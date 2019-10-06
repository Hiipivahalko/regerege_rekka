package dataStructures;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MLinkedListTest {

    private MLinkedList<Integer> list1;
    private MLinkedList<String> list2;
    private Random r = new Random();

    @Before
    public void setUp() throws Exception {
        this.list1 = new MLinkedList<>();
        this.list2 = new MLinkedList<>();
    }

    @Test
    public void addLast() {
        assertTrue(list1.size() == 0);
        int next = 5;
        list1.addLast(next);
        assertTrue(list1.size() == 1);
        assertTrue(list1.getLast() == next);
        assertTrue(list1.getFirst() == next);
    }

    @Test
    public void addLast2() {
        assertTrue(list1.size() == 0);
        int next = r.nextInt();
        int first = 1;
        list1.addLast(first);
        for (int i = 0; i < 100; i++) {
            list1.addLast(next);
        }
        assertTrue(list1.size() == 101);
        assertTrue(list1.getLast() == next);
        int next2 = r.nextInt();
        list1.addLast(next2);
        assertTrue(list1.getLast() == next2);
        assertTrue(list1.getFirst() == first);

    }

    @Test
    public void addLast3() {
        assertTrue(list1.size() == 0);
        int first = r.nextInt();
        int second = r.nextInt();
        int third = r.nextInt();
        list1.addLast(first);
        list1.addLast(second);
        assertTrue(second == list1.getLast());
        assertTrue(first == list1.getFirst());

        list1.addLast(third);
        assertTrue(third == list1.getLast());
        assertTrue(first == list1.getFirst());
    }


    @Test
    public void addFirst() {
        assertTrue(list1.size() == 0);
        int next = 10;
        list1.addFirst(next);
        assertTrue(list1.size() == 1);
        assertTrue(list1.getLast() == next);
        assertTrue(list1.getFirst() == next);

    }

    @Test
    public void addFirst2() {
        assertTrue(list1.size() == 0);
        int next = 5;
        int last = 101;
        list1.addFirst(last);
        for (int i = 0; i < 100; i++) {
            list1.addFirst(next);
        }
        assertTrue(list1.size() == 101);
        assertTrue(list1.getFirst() == next);
        int next2 = 6;
        list1.addFirst(next2);
        assertTrue(list1.getFirst() == next2);
        assertTrue(list1.getLast() == 101);
    }

    @Test
    public void addFirst3() {
        assertTrue(list1.size() == 0);
        int first = r.nextInt();
        int second = r.nextInt();
        int third = r.nextInt();
        list1.addFirst(first);
        list1.addFirst(second);
        assertTrue(second == list1.getFirst());
        assertTrue(first == list1.getLast());

        list1.addFirst(third);
        assertTrue(third == list1.getFirst());
        assertTrue(first == list1.getLast());
    }

    @Test
    public void popLast() {
        assertTrue(list1.size() == 0);
        int next = 2;
        int next2 = 9;

        list1.addLast(next);
        assertTrue(list1.getLast() == list1.getFirst() && list1.getFirst() == next);
        list1.addLast(next2);

        int res = list1.popLast();
        assertTrue(res == next2);
        assertTrue(list1.size() != 0);
        assertTrue(list1.getFirst() != null && list1.getLast() != null);
        int res2 = list1.popLast();
        assertTrue(res2 == next);

        assertTrue(list1.size() == 0);
        assertTrue(list1.getFirst() == null && list1.getLast() == null);
    }

    @Test
    public void popLast2() {
        assertTrue(list1.size() == 0);
        int i1 = r.nextInt();
        int i2 = r.nextInt();
        list1.addFirst(i1);
        list1.addFirst(i2);

        int res = list1.popLast();
        assertTrue(res == i1);
        int res2 = list1.popLast();
        assertTrue(res2 == i2);

        assertTrue(list1.size() == 0);
        assertTrue(list1.getFirst() == null && list1.getLast() == null);
    }

    @Test
    public void popLast3() {
        int i1 = r.nextInt();
        int i2 = r.nextInt();
        int i3 = r.nextInt();
        assertTrue(list1.size() == 0);

        list1.addFirst(i1);
        list1.addLast(i2);
        list1.addFirst(i3);
        assertTrue(list1.size() == 3);
        int res1 = list1.popLast();
        assertTrue(res1 == i2);
        int res2 = list1.popLast();
        assertTrue(res2 == i1);
        assertTrue(list1.getLast() == list1.getFirst());
        int res3 = list1.popLast();
        assertTrue(res3 == i3);
        assertTrue(list1.size() == 0);
        assertTrue(list1.getFirst() == null && list1.getLast() == null);
    }

    @Test
    public void popFirst() {
        assertTrue(list1.size() == 0);
        int next = 2;
        int next2 = 9;

        list1.addFirst(next);
        assertTrue(list1.getLast() == list1.getLast() && list1.getLast() == next);
        list1.addFirst(next2);

        int res = list1.popFirst();
        assertTrue(res == next2);
        assertTrue(list1.size() != 0);
        assertTrue(list1.getFirst() != null && list1.getLast() != null);
        int res2 = list1.popFirst();
        assertTrue(res2 == next);

        assertTrue(list1.size() == 0);
        assertTrue(list1.getFirst() == null && list1.getLast() == null);

    }

    @Test
    public void popFirst2() {
        assertTrue(list1.size() == 0);
        int i1 = r.nextInt();
        int i2 = r.nextInt();
        list1.addLast(i1);
        list1.addLast(i2);

        int res = list1.popFirst();
        assertTrue(res == i1);
        int res2 = list1.popFirst();
        assertTrue(res2 == i2);

        assertTrue(list1.size() == 0);
        assertTrue(list1.getFirst() == null && list1.getLast() == null);
    }

    @Test
    public void popFirst3() {
        int i1 = r.nextInt();
        int i2 = r.nextInt();
        int i3 = r.nextInt();
        assertTrue(list1.size() == 0);

        list1.addLast(i1);
        list1.addFirst(i2);
        list1.addLast(i3);
        assertTrue(list1.size() == 3);
        int res1 = list1.popFirst();
        assertTrue(res1 == i2);
        int res2 = list1.popFirst();
        assertTrue(res2 == i1);
        assertTrue(list1.getLast() == list1.getFirst());
        int res3 = list1.popFirst();
        assertTrue(res3 == i3);
        assertTrue(list1.size() == 0);
        assertTrue(list1.getFirst() == null && list1.getLast() == null);
    }

    @Test
    public void mix() {
        int i1 = 10;
        int i2 = 20;
        int i3 = 30;
        assertTrue(list1.size() == 0);

        list1.addLast(i1);
        list1.addLast(i2);
        list1.addLast(i3);


        assertTrue(list1.size() == 3);
        int res1 = list1.popFirst();

        assertTrue(res1 == i1);
        int res2 = list1.popLast();
        assertTrue(res2 == i3);
        assertTrue(list1.size() == 1);
        int res3 = list1.popLast();
        assertTrue(res3 == i2);

        assertTrue(list1.size() == 0);
        assertTrue(list1.getFirst() == null && list1.getLast() == null);
    }


}