package debug;

import automaton.Node;

import java.util.LinkedList;

public class DisplayAutomaton {

    public DisplayAutomaton() {
    }

    public void displayNFA(LinkedList<Node> mNfa) {
        System.out.println("-----NFA-----");

        for (Node n : mNfa) {
            if (n.isGoalNode()) System.out.println("goal " + n.getId());
            for (char i : n.getTransfers().keySet()) {
                for (Node n2 : n.getTransfers().get(i)) {
                    System.out.println(n.getId() + " --> " + n2.getId() + " : " + i);
                }
            }
        }
    }

    public void displayDFA(LinkedList<Node> mDfa) {
        System.out.println("------DFA-------");

        for (Node n : mDfa) {
            if (n.isGoalNode()) System.out.println("goal " + n.getNodeSet());
            for (char c : n.getTransfers().keySet()) {
                for (Node n2 : n.getTransfers().get(c)) {
                    System.out.println(n.getNodeSet() + "  ->  " + n2.getNodeSet() + " : " + c);
                }
            }
        }
    }
}
