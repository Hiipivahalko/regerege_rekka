package automaton;

import automaton.node.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.*;

public class DFABuilderTest {

    private String regex1 = "(joo)*jee|hahp*";
    private String regex2 = "test";
    private NFABuilder nfa1 = new NFABuilder();
    private NFABuilder nfa2 = new NFABuilder();
    private Node nfaStartNode1;
    private Node nfaStartNode2;
    private DFABuilder dfa;
    private DFABuilder dfa2;
    private Node node1 = new Node(1);
    private Node node2 = new Node(2);
    private Node node3 = new Node(3);
    private Node node4 = new Node(4);
    private char epsilonChar = 0;

    @Before
    public void setUp() throws Exception {
        this.nfaStartNode1 = this.nfa1.build(regex1);
        this.nfaStartNode2 = this.nfa2.build(regex2);
        this.dfa = new DFABuilder(nfa1.getNodeId(), nfa1.getInputChars());
        this.dfa2 = new DFABuilder(nfa2.getNodeId(), nfa2.getInputChars());
        this.dfa.buildDFA(this.nfaStartNode1);
        this.dfa2.buildDFA(this.nfaStartNode2);
    }

    /**
     * Testataan NFA1 verkon tulosolmulle epsilonTransfers funktiota,
     * jotta päästään tarpeeksi moneen solmuun
     */
    @Test
    public void epsilonTransfers1() {

        Set<Node> s1 = new HashSet<>();
        s1.add(nfaStartNode1);
        assertTrue(s1.size() == 1);

        Set<Node> epsilons1 = dfa.epsilonTransfers(s1);
        //System.out.println(epsilons1.size());
        assertTrue(epsilons1.size() == 6);

    }

    /**
     * NFA2 tulosolmulle tehdää testaus epsilonsiirroille, niitä ei pitäisi olla
     */
    @Test
    public void epsilonTransfer2() {
        Set<Node> s2 = new HashSet<>();
        s2.add(nfaStartNode2);

        assertTrue(s2.size() == 1);
        Set<Node> epsilons2 = dfa2.epsilonTransfers(s2);
        assertTrue(epsilons2.size() == 1);
        int i2 = 0;
        for (Node node : epsilons2) {
            assertTrue(node.getId() == i2);
            i2++;
        }
    }

    /**
     * Testataan DFA1 tulosolmun siirtoa muutamalla symbolilla verkossa
     */
    @Test
    public void transferWhitChar1() {
        Set<Node> s1 = new HashSet<>();
        s1.add(nfaStartNode1);
        assertTrue(s1.size() == 1);

        Set<Node> epsilons1 = dfa.epsilonTransfers(s1);
        assertTrue(epsilons1.size() == 6);

        Set<Node> res = dfa.transferWhitChar('j', epsilons1);
        assertTrue(res.size() == 2);

        boolean found1 = false;
        boolean found2 = false;
        for (Node node : res) {
            if (node.getTransfers().get(epsilonChar).get(0).getTransfers().containsKey('o')) found1 = true;
            if (node.getTransfers().get(epsilonChar).get(0).getTransfers().containsKey('e')) found2 = true;
        }
        // tarkastetaan että saadut solmut johtavat oikeaan suuntaan verkossa
        assertTrue(found1 && found2);

        Set<Node> res2 = dfa.transferWhitChar('h', s1);
        assertTrue(res2.size() == 1);

        Set<Node> res3 = dfa.transferWhitChar('a', s1);
        assertTrue(res3.size() == 0);

    }

    /**
     *
     */
    @Test
    public void transferWhitChar2() {
        Set<Node> s2 = new HashSet<>();
        s2.add(nfaStartNode2);

        assertTrue(s2.size() == 1);
        Set<Node> epsilons2 = dfa2.epsilonTransfers(s2);
        assertTrue(epsilons2.size() == 1);


        Set<Node> res = dfa2.transferWhitChar('t', epsilons2);
        assertTrue(res.size() == 1);

        for (Node node : res ) {
            assertTrue(node.getTransfers().size() == 1 && node .getTransfers().containsKey(epsilonChar));

        }

        Set<Node> res2 = dfa2.transferWhitChar('k', epsilons2);
        assertTrue(res2.size() == 0);
    }

    /**
     * Testataan että dfa:n aloitus solmu saa halutun arvo
     */
    @Test
    public void DFAStartNodeFromNFA() {
        Node startDFANode = dfa2.DFAStartNodeFromNFA(nfaStartNode2);
        assertTrue(startDFANode.getNodeSet().size() == 1);

        for (Node node : startDFANode.getNodeSet()) {
            assertTrue(node.getTransfers().get('t') != null);
        }
    }

    /**
     * Tarkistetaan että dfa:n aloitus solmu halutunlainen
     * säännöllisellä lausekkeella on '*' sekä '\' aloitusrakenne,
     * ensimmäiselle solmulle
     */
    @Test
    public void DFAStartNodeFromNFA2() {
        Node start = dfa.DFAStartNodeFromNFA(nfaStartNode1);
        boolean jMove = false;
        boolean hMove = false;
        for (Node node : start.getNodeSet()) {
            if (node.getTransfers().get('j') != null) jMove = true;
            if (node.getTransfers().get('h') != null) hMove = true;
        }
        assertTrue(start.getNodeSet().size() == 6);
        assertTrue(jMove);
        assertTrue(hMove);
    }
}