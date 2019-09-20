package automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node {

    private int id;
    //private ArrayList<Transfer> nfaTransfers;
    private Map<Character, ArrayList<Node>> nfaTransfers;
    private ArrayList<Transfer> dfaTransfers;
    private boolean isGoalNode;
    private Set<Node> nfaNodeSet;

    public Node(int id) {
        this.id = id;
        this.nfaTransfers = new HashMap<Character, ArrayList<Node>>();//new ArrayList<>();
        this.isGoalNode = false;
    }

    public Node(int id, Set<Node> nfaNodeSet) {
        this.id = id;
        this.nfaNodeSet = nfaNodeSet;
        this.dfaTransfers = new ArrayList<>();
    }

    public boolean isGoalNode() {
        return isGoalNode;
    }

    public int getId() {
        return id;
    }

    public Map<Character, ArrayList<Node>> getNfaTransfers() {
        return nfaTransfers;
    }

    public Set<Node> getNfaNodeSet() {
        return nfaNodeSet;
    }

    public void setGoalNode(boolean goalNode) {
        isGoalNode = goalNode;
    }

    public void addTransfer(Node n, char c) {
        if (!nfaTransfers.containsKey(c)) {
            nfaTransfers.put(c, new ArrayList<Node>());
        }
        nfaTransfers.get(c).add(n);

    }

    public void addTransfer(Set<Node> s, char c) {
        dfaTransfers.add(new Transfer(s, c));
    }


}
