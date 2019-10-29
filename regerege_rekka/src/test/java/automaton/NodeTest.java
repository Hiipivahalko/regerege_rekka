package automaton;

import automaton.node.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    private Node node;
    private int nodeid;

    @Before
    public void set() {
        this.nodeid = 1;
        this.node = new Node(nodeid);
    }

    @Test
    public void addTransfer() {

        assertTrue(this.node.getTransfers().size() == 0);

        Node n = new Node(++nodeid);
        Node n2 = new Node(++nodeid);

        node.addTransfer(n, 'a');

        assertTrue(node.getTransfers().size() == 1);
        assertTrue(node.getTransfers().get('a').size() == 1);
        node.addTransfer(n2, 'a');
        assertTrue(node.getTransfers().size() == 1);
        assertTrue(node.getTransfers().get('a').size() == 2);
    }

}