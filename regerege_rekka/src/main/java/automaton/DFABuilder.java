package automaton;

import automaton.node.Node;
import dataStructures.MLinkedList;
import dataStructures.Mstack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFABuilder {

    private int nodeId;
    private MLinkedList<Node> allDFANodes;
    private Set<Character> inputChars;
    private Mstack<Node> unprocessedDFANodes;
    private char epsilon;
    private char allChars;
    private boolean isGoalNode;
    private Map<Set<Node>, Node> dfaNodes;


    /**
     * Rakentaa DFA automaatin annetusta NFA automaatista
     * @param nodeId - seuraavan solmun id
     * @param  - NFA automaatti
     * @param inputChars - säännöllisessä lausekkeessa käytetyt symbolit
     */
    public DFABuilder(int nodeId, Set<Character> inputChars) {
        this.nodeId = nodeId;
        this.allDFANodes = new MLinkedList<>();
        this.inputChars = inputChars;
        this.unprocessedDFANodes = new Mstack<>();
        this.epsilon = 0;
        this.allChars = 1;
        this.isGoalNode = false;
        this.dfaNodes = new HashMap<>();
    }

    // Getterit

    public int getNodeId() {
        return nodeId;
    }

    public MLinkedList<Node> getAllDFANodes() {
        return allDFANodes;
    }

    public Set<Character> getInputChars() {
        return inputChars;
    }

    public Mstack<Node> getUnprocessedDFANodes() {
        return unprocessedDFANodes;
    }

    public char getEpsilon() {
        return epsilon;
    }


    // Setterit

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setAllDFANodes(MLinkedList<Node> allDFANodes) {
        this.allDFANodes = allDFANodes;
    }

    public void setInputChars(Set<Character> inputChars) {
        this.inputChars = inputChars;
    }

    public void setUnprocessedDFANodes(Mstack<Node> unprocessedDFANodes) {
        this.unprocessedDFANodes = unprocessedDFANodes;
    }

    public void setEpsilon(char epsilon) {
        this.epsilon = epsilon;
    }


    //////

    /**
     * Rakentaa DFA automaatin käymällä NFA:n siirtymä funtiot läpi
     */
    public Node buildDFA(Node nfaStartNode) {

        Node dfaStartNode = DFAStartNodeFromNFA(nfaStartNode);

        while (!unprocessedDFANodes.empty()) {
            Node curr = unprocessedDFANodes.pop();
            for (char c : inputChars) {
                isGoalNode = false;
                findPossibleDFANodeMove(curr, c);
            }
        }
        return dfaStartNode;

    }

    /**
     * Esikäsittelee DFA:n ja luo DFA:n aloitussolmun. Tämä saadaan nfa:n aloitussolmusta
     * sekä siitä päästävillä epsilon siirroilla
     */
    public Node DFAStartNodeFromNFA(Node nfaStartNode) {
        Set<Node> nfaStarNodes = new HashSet<>();
        nfaStarNodes.add(nfaStartNode);

        Set<Node> dfaStartNodes = epsilonTransfers(nfaStarNodes);
        Node dfaStartNode = new Node(++nodeId,dfaStartNodes);
        allDFANodes.addFirst(dfaStartNode);
        dfaNodes.put(dfaStartNodes, dfaStartNode);

        unprocessedDFANodes.push(dfaStartNode);

        return dfaStartNode;
    }

    /**
     * Lisää solmujoukkoon solmut joihin päästään epsilon siirroilla.
     * @param nodeSet - joukko solmuja
     * @return solmujoukon, johon on lisätty "epsilonsolmut"
     */
    public Set<Node> epsilonTransfers(Set<Node> nodeSet) {
        Mstack<Node> nodesToProcess = new Mstack<>();
        for (Node n : nodeSet) {
            nodesToProcess.push(n);
        }

        while (!nodesToProcess.empty()) {
            Node curr = nodesToProcess.pop();
            if (curr.getTransfers().containsKey(epsilon)) {
                for (Node next : curr.getTransfers().get(epsilon)) {
                    if (!nodeSet.contains(next)) {
                        nodeSet.add(next);
                        if (next.isGoalNode()) isGoalNode = true;
                        nodesToProcess.push(next);
                    }
                }
            }
        }
        return nodeSet;
    }

    /**
     * Funktio etsii mahdolliset siirrot annetusta DFA solmusta, joka sisältää nfa solmujoukon
     * @param curr - DFA solmu
     * @param ch - symboli jolla yritetään liikkua automaatissa
     */
    public void findPossibleDFANodeMove(Node curr, char ch) {
        Set<Node> nextDFANode = transferWhitChar(ch, curr.getNodeSet());
        if (nextDFANode.isEmpty()) return;

        nextDFANode = epsilonTransfers(nextDFANode);

        if (!dfaNodes.containsKey(nextDFANode)) {
            Node newDFANode = new Node(++nodeId, nextDFANode);
            if (isGoalNode) newDFANode.setGoalNode(true);
            allDFANodes.addLast(newDFANode);
            unprocessedDFANodes.push(newDFANode);
            dfaNodes.put(nextDFANode, newDFANode);
            curr.addTransfer(newDFANode, ch);

        } else {
            curr.addTransfer(dfaNodes.get(nextDFANode), ch);
        }
    }

    /**
     * Etsii mahdolliset siirtymät annetulla solmujoukolla,
     * joka on uusi DFA-solmu ilman mahdollisia epsilon siirtymiä
     * @param inputCh - symboli jolla liikutaan
     * @param nodeSet - DFA solmu josta yritetään liikkua
     * @return - DFA solmu ilman mahdollisia epsilon siirtymiä
     */
    public Set<Node> transferWhitChar(char inputCh, Set<Node> nodeSet) {
        Set<Node> result = new HashSet<>();
        for (Node n : nodeSet) {
            if (n.getTransfers().containsKey(inputCh)) {
                for (Node next : n.getTransfers().get(inputCh)) {
                    if (next.isGoalNode()) isGoalNode = true;
                    result.add(next);
                }
            }
        }
        return result;
    }

}
