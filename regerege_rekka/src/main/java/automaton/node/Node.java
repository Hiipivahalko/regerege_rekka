package automaton.node;

import dataStructures.MList;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Node implements Comparable<Node> {

    private int id;
    private Map<Character, MList<Node>> transfers;
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

    public Map<Character, MList<Node>> getTransfers() {
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
            transfers.put(c, new MList<Node>());
        }
        transfers.get(c).add(n);

    }

    /**
     * Palauttaa Node objektin hasharvon, jota voidaan käyttää esim hajautustauluissa
     * @return
     */
    @Override
    public int hashCode() {
        return getId();
    }

    /**
     * Kertoo ovatko kaksi objectia samoja
     * @param o - vertailtava objekti
     * @return - palauttaa true jos ovat samoja, muuten false
     */
    @Override
    public boolean equals(Object o) {
        Node n = (Node) o;

        if (this.getClass() != o.getClass()) return false;

        if (id != n.getId()) return false;
        if (nodeSet != n.getNodeSet()) return false;
        if (isGoalNode != n.isGoalNode()) return false;
        //if (transfers != n.getTransfers()) return false;

        return true;
    }

    /**
     * Solmun tulostus muoto, joka on sen 'NodeId: id'
     * @return
     */
    @Override
    public String toString() {
        return "NodeId: " + getId() ;
    }

    /**
     * Node luokan vertailu funktio
     * Palauttaa kummalla solmulla on pienempi ID
     * @param o - vertailtava solmu
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return getId() - o.getId();
    }
}
