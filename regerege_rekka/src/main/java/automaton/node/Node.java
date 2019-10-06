package automaton.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Node {

    private int id;
    private Map<Character, ArrayList<Node>> transfers;
    private boolean isGoalNode;
    private Set<Node> nodeSet;

    /**
     * NFA- ja DFA-verkon solmu
     */
    public Node(int id) {
        this.id = id;
        this.transfers = new HashMap<>();
        this.isGoalNode = false;
        this.nodeSet = null;
    }

    public Node(int id, Set<Node> nfaNodeSet) {
        this(id);
        this.nodeSet = nfaNodeSet;
    }

    // Getterit

    public boolean isGoalNode() {
        return isGoalNode;
    }

    public int getId() {
        return id;
    }

    public Map<Character, ArrayList<Node>> getTransfers() {
        return transfers;
    }

    // Setterit

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public void setGoalNode(boolean goalNode) {
        isGoalNode = goalNode;
    }

    ///////////////

    /**
     * Lisää solmulle yksisuuntaisenkaaren solmuun n, annetulla symbolilla c
     * @param n - kohde solmu
     * @param c - kaarella
     */
    public void addTransfer(Node n, char c) {
        if (!transfers.containsKey(c)) {
            transfers.put(c, new ArrayList<Node>());
        }
        transfers.get(c).add(n);

    }

    @Override
    public String toString() {
        return "NodeId: " + getId() ;
    }


}
