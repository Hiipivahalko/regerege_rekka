package automaton;

import java.util.*;

public class DFABuilder {

    private int nodeId;
    private LinkedList<Node> nfa;
    private LinkedList<Node> allDFANodes;
    private Set<Character> inputChars;
    private Set<Node> allReadyProcessed;
    private Stack<Node> unprocessedDFANodes;
    private Node startNode;
    private char epsilon;
    private boolean isGoalNode;


    /**
     * Rakentaa DFA automaatin annetusta NFA automaatista
     * @param nodeId - seuraavan solmun id
     * @param nfa - NFA automaatti
     * @param inputChars - säännöllisessä lausekkeessa käytetyt symbolit
     */
    public DFABuilder(int nodeId, LinkedList<Node> nfa, Set<Character> inputChars) {
        this.nodeId = nodeId;
        this.nfa = nfa;
        this.allDFANodes = new LinkedList<>();
        this.inputChars = inputChars;
        this.allReadyProcessed = new HashSet<>();
        this.unprocessedDFANodes = new Stack<>();
        this.epsilon = '#';
        this.startNode = null;
        this.isGoalNode = false;
    }

    // Getterit

    public int getNodeId() {
        return nodeId;
    }

    public LinkedList<Node> getNfa() {
        return nfa;
    }

    public LinkedList<Node> getAllDFANodes() {
        return allDFANodes;
    }

    public Set<Character> getInputChars() {
        return inputChars;
    }

    public Set<Node> getAllReadyProcessed() {
        return allReadyProcessed;
    }

    public Stack<Node> getUnprocessedDFANodes() {
        return unprocessedDFANodes;
    }

    public char getEpsilon() {
        return epsilon;
    }

    public Node getStartNode() {
        return startNode;
    }


    // Setterit

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setNfa(LinkedList<Node> nfa) {
        this.nfa = nfa;
    }

    public void setAllDFANodes(LinkedList<Node> allDFANodes) {
        this.allDFANodes = allDFANodes;
    }

    public void setInputChars(Set<Character> inputChars) {
        this.inputChars = inputChars;
    }

    public void setAllReadyProcessed(Set<Node> allReadyProcessed) {
        this.allReadyProcessed = allReadyProcessed;
    }

    public void setUnprocessedDFANodes(Stack<Node> unprocessedDFANodes) {
        this.unprocessedDFANodes = unprocessedDFANodes;
    }

    public void setEpsilon(char epsilon) {
        this.epsilon = epsilon;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    //////

    /**
     * Rakentaa DFA automaatin käymällä NFA:n siirtymä funtiot läpi
     */
    public void buildDFA() {

        preprocessDFAFromNFA();

        while (!unprocessedDFANodes.empty()) {
            Node curr = unprocessedDFANodes.pop();
            for (char c : inputChars) {
                isGoalNode = false;
                findPossibleDFANodeMove(curr, c);
            }
        }
    }

    /**
     * Esikäsittelee DFA:n ja luo DFA:n aloitus solmun. Tämä saadaan nfa:n aloitus-solmusta
     * sekä siitä päästävillä epsilon siirroilla
     */
    public void preprocessDFAFromNFA() {
        Node nfaStartNode = nfa.get(0);
        Set<Node> nfaStarNodes = new HashSet<>();
        nfaStarNodes.add(nfaStartNode);

        Set<Node> dfaStartNodes = epsilonTransfers(nfaStarNodes);
        Node dfaStartNode = new Node(++nodeId,dfaStartNodes);
        startNode = dfaStartNode;
        allDFANodes.add(dfaStartNode);

        unprocessedDFANodes.add(dfaStartNode);
    }

    /**
     * Lisää solmujoukkoon solmut joihin päästään epsilon siirroilla.
     * @param nodeSet - joukko solmuja
     * @return solmujoukon, johon on lisätty "epsilonsolmut"
     */
    public Set<Node> epsilonTransfers(Set<Node> nodeSet) {
        Stack<Node> nodesToProcess = new Stack<>();
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
     * Funktio etsii mahdolliset siirrot annetusta DFA solmusta, joka sisältää nfa solmu jokuon
     * @param curr - DFA solmu
     * @param ch - symboli jolla yritetään liikkua automaatissa
     */
    public void findPossibleDFANodeMove(Node curr, char ch) {
        Set<Node> nextDFANode = transferWhitChar(ch, curr.getNodeSet());
        if (nextDFANode.isEmpty()) return;

        nextDFANode = epsilonTransfers(nextDFANode);
        boolean found = false;
        Node allReadyAtDFA = curr;

        for (Node test : allReadyProcessed) {
            if ( nextDFANode.equals(test.getNodeSet())) {
                allReadyAtDFA = test;
                found = true;
                break;
            }
        }

        if (!found) {
            Node newDFANode = new Node(++nodeId, nextDFANode);
            if (isGoalNode) newDFANode.setGoalNode(true);
            allDFANodes.add(newDFANode);
            unprocessedDFANodes.push(newDFANode);
            allReadyProcessed.add(newDFANode);
            curr.addTransfer(newDFANode, ch);

        } else {
            curr.addTransfer(allReadyAtDFA, ch);
        }
    }

    /**
     * Etsii mahdolliset siirtymät annetulla solmujoukolla, joka on uusi DFA solmu ilman mahdollisia epsilon siirtymiä
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
