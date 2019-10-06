package automaton;


import automaton.node.Node;
import org.junit.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import static org.junit.Assert.*;

public class NFABuilderTest {

    private NFABuilder nb;
    private String regex1 = "test";
    private String regex2 = "t(es)*t";
    private String regex3 = "t*est";
    private char epsilonMove = '#';

    @Before
    public void setUp() throws Exception {
        this.nb = new NFABuilder();
        this.nb.build(regex2);
    }

    @Test
    public void checkIfOperator() {
        char c1 = 'a';
        char c2 = '(';
        char c3 = '(';
        char c4 = '*';
        char c5 = '|';

        assertTrue(!nb.checkIfOperator(c1));
        assertTrue(nb.checkIfOperator(c2));
        assertTrue(nb.checkIfOperator(c3));
        assertTrue(nb.checkIfOperator(c4));
        assertTrue(nb.checkIfOperator(c5));
    }

    @Test
    public void preprocess() {
        String result1 = nb.preprocess(regex2);
        String answer1 = "t?(e?s)*?t";

        String result2 = nb.preprocess(regex1);
        String answer2 = "t?e?s?t";

        String result3 = nb.preprocess(regex3);
        String answer3 = "t*?e?s?t";

        //assertTrue(result1.equals(answer1));
        assertEquals(result1, answer1);
        assertEquals(result2, answer2);
        assertEquals(result3, answer3);
    }

    @Test
    public void push() {
        int stackSize = nb.getNfaStack().size();
        char c = 'a';
        nb.push(c);
        assertTrue(nb.getNfaStack().size() == stackSize+1);
        LinkedList<Node> l = nb.getNfaStack().pop();
        assertTrue(l.size() == 2);

        Node a = l.get(0);
        assertTrue(a.getTransfers().containsKey(c));
        assertTrue(!a.getTransfers().containsKey('b'));
        assertTrue(a.getTransfers().get(c).size() == 1);

    }

    @Test
    public void concat() {
        nb.setNfaStack(new Stack<>());

        nb.push('a');
        nb.push('b');

        nb.concat();

        assertTrue(nb.getNfaStack().size() == 1);
        assertTrue(nb.getNfaStack().get(0).size() == 4);
        assertTrue(nb.getNfaStack().get(0).get(1).getTransfers().containsKey(epsilonMove));
    }

    @Test
    public void union() {
        nb.setNfaStack(new Stack<>());

        nb.push('a');
        int id = nb.getNfaStack().peek().get(0).getId();
        nb.push('b');
        int id2 = nb.getNfaStack().peek().get(0).getId();

        nb.union();
        ArrayList<Node> startNode_moves = nb.getNfaStack().peek().get(0).getTransfers().get(epsilonMove);

        assertTrue(nb.getNfaStack().size() == 1);
        assertTrue(nb.getNfaStack().get(0).size() == 6);

        assertTrue(startNode_moves.size() == 2);
        assertTrue(startNode_moves.get(0).getId() == id);
        assertTrue(startNode_moves.get(1).getId() == id2);
    }

    @Test
    public void star() {
        nb.setNfaStack(new Stack<>());

        nb.push('a');
        int idA = nb.getNfaStack().peek().get(0).getId();
        nb.push('b');
        int idB = nb.getNfaStack().peek().get(0).getId();

        nb.concat();

        int sizeBeforeStart = nb.getNfaStack().peek().size();
        //System.out.println(idA + " " + idB + " " + sizeBeforeStart);

        nb.star();

        assertTrue(nb.getNfaStack().peek().size() == sizeBeforeStart + 2);
        int listSize = nb.getNfaStack().peek().size();
        LinkedList<Node> top = nb.getNfaStack().peek();
        assertTrue(top.get(listSize-2).getTransfers().get(epsilonMove).size() == 2);
        assertTrue(top.get(listSize-2).getTransfers().get(epsilonMove).get(1).getId() == idA);
        assertTrue(top.get(0).getTransfers().get(epsilonMove).size() == 2);
        assertTrue(top.get(0).getTransfers().get(epsilonMove).get(0).getId() == top.get(listSize-1).getId());
        assertTrue(top.get(0).getTransfers().get(epsilonMove).get(1).getId() == idA);
    }


}