package dataStructures;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MPriorityQueueTest {

    private MPriorityQueue<Integer> pq;
    private Random random;

    @Before
    public void setUp() throws Exception {
        this.pq = new MPriorityQueue<>();
        this.random = new Random();
    }

    /**
     * Testataan kekoon objektin laittoa
     */
    @Test
    public void insert() {
        assertTrue(pq.size() == 0);

        pq.add(4);
        assertTrue(pq.size() == 1);

        pq.add(1);
        assertTrue(pq.size() == 2);
    }

    /**
     * Testataan objektien laittoa kekoon "isommin"
     */
    @Test
    public void insert2() {
        assertTrue(pq.size() == 0);
        int next = 0;
        for (int i = 0; i < 1000; i++) {
            next = random.nextInt(1000000);
            pq.add(next);
        }

        assertTrue(pq.size() == 1000);
    }



    /**
     * Testataan kekosta "pienimmän" objektin poistoa
     */
    @Test
    public void deleteMin() {

        assertTrue(pq.size() == 0);

        pq.add(1);
        int val = pq.poll();
        assertTrue(val == 1);
    }

    /**
     * Testataan keosta poistamista random arvoilla
     * ja testataan että varmasti koen päällimmäisenä on pienin random arvo
     */
    @Test
    public void deleteMin2() {
        assertTrue(pq.size() == 0);

        int next = 0;
        int smallest = Integer.MAX_VALUE;

        for (int i = 0; i < 300; i++) {
            next = random.nextInt(10000);
            if (next < smallest) smallest = next;
            pq.add(next);
        }

        int popValue = pq.poll();

        assertTrue(smallest == popValue);
        assertTrue(pq.size() == 299);
    }

    /**
     * Testataan keon tyhjentämistä kokonaan
     */
    @Test
    public void deleteMin3() {
        assertTrue(pq.size() == 0);

        pq.add(4);
        pq.add(5);
        pq.add(2);
        assertTrue(pq.size() == 3);

        assertTrue(pq.poll() == 2);
        assertTrue(pq.poll() == 4);
        assertTrue(pq.poll() == 5);

        assertTrue(pq.size() == 0);

    }
}