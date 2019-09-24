package automaton;


import org.junit.*;

import java.util.LinkedList;
import java.util.Stack;

import static org.junit.Assert.*;

public class NFABuilderTest {

    private NFABuilder nb;
    private String regex1 = "test";
    private String regex2 = "t(es)*t";
    private String regex3 = "t*est";

    @Before
    public void setUp() throws Exception {
        this.nb = new NFABuilder(regex2);
        this.nb.build();
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
        assertTrue(a.getNfaTransfers().containsKey(c));
        assertTrue(!a.getNfaTransfers().containsKey('b'));
        assertTrue(a.getNfaTransfers().get(c).size() == 1);

    }

    @Test
    public void concat() {
        nb.setNfaStack(new Stack<>());

        nb.push('a');
        nb.push('b');

        nb.concat();

        assertTrue(nb.getNfaStack().size() == 1);
        assertTrue(nb.getNfaStack().get(0).size() == 4);
        assertTrue(nb.getNfaStack().get(0).get(1).getNfaTransfers().containsKey('#'));
    }


    @Test
    public void getFinalNfa() {
    }

    @Test
    public void getNodeId() {
    }

    @Test
    public void getInputChars() {
    }

    @Test
    public void build() {
    }

    @Test
    public void precedence() {
    }


}