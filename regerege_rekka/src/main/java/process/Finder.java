package process;

import automaton.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Finder {

    private String currLine;
    private Node startNode;
    private Set<Character> nextMoves;
    private int idx;
    private int leftIdx;
    private int rightIdx;
    private Node currNode;
    private boolean insideGraph;
    private final String  ANSI_RED  = "\u001B[31m";
    private final String  ANSI_NORMAL = "\u001B[0m";
    private StringBuilder builder;

    public Finder(Node startNode) {
        this.currLine = "";
        this.startNode = startNode;
        this.currNode = null;
        this.idx = 0;
        this.nextMoves = new HashSet<>();
        this.leftIdx = 0;
        this.rightIdx = 0;
        this.insideGraph = false;
    }


    public void findSubstring(String nextLine) {
        currLine = nextLine;
        currNode = startNode;

        idx = 0;
        char currChar = 'a';
        insideGraph= false;
        while (idx < currLine.length()) {
            currChar = currLine.charAt(idx);
            if (!insideGraph && currNode.getTransfers().get(currChar) == null) {
                idx++;
                continue;
            } else {
                if (moveInsideAutomaton(currChar)) {
                    System.out.println(currLine);
                    return;
                }
            }
            if (insideGraph) idx++;
        }
    }

    public boolean moveInsideAutomaton(char currChar) {
        if (!insideGraph) leftIdx = idx;
        insideGraph = true;
        if (currNode.getTransfers().get(currChar) == null) {
            insideGraph = false;
            currNode = startNode;
        } else {
            if (currNode.getTransfers().get(currChar).size() == 1) {
                currNode = currNode.getTransfers().get(currChar).get(0);
            } else {
                System.out.println("DFA build wrong");
                System.exit(1);
            }

            if (currNode.isGoalNode()) {
                rightIdx = idx;
                builder.append(ANSI_RED);
                builder.append(currLine.substring(leftIdx, rightIdx));
                insideGraph = false;
                return true;
            }

        }
        return false;
    }

}
