package process;

import automaton.node.Node;

import java.util.HashSet;
import java.util.Set;

public class Finder {

    private Node startNode;
    private int idx;
    private int leftIdx;
    private int rightIdx;
    private Node currNode;
    private boolean insideGraph;
    private final String  ANSI_RED  = "\u001B[31m";
    private final String  ANSI_NORMAL = "\u001B[0m";
    //private StringBuilder builder;

    /**
     * Tutkii toteuttaako annetut merkkijonot DFA-verkkoa
     * @param startNode
     */
    public Finder(Node startNode) {
        this.startNode = startNode;
        this.currNode = null;
        this.idx = 0;
        this.leftIdx = 0;
        this.rightIdx = 0;
        this.insideGraph = false;
    }

    /**
     * Käy läpi DFA-verkkoa annetulla merkkijonolla
     * @param line - Tutkittava merkkijono
     * @return - jos merkkijono läpäisee verkon, niin metodi palauttaa true arvon, muuten false
     */
    public boolean findSubstring(String line) {
        currNode = startNode;

        idx = 0;
        char currChar = 'a';
        insideGraph= false;
        while (idx < line.length()) {
            currChar = line.charAt(idx);
            if (!insideGraph && currNode.getTransfers().get(currChar) == null) {
                idx++;
                continue;
            } else {
                if (moveInsideAutomaton(currChar)) {
                    return true;
                }
            }
            if (insideGraph) idx++;
        }
        return false;
    }

    /**
     * Liikkuua DFA-verkon sisällä jos pystyy
     * @param currChar - symboli jolla yritetään liikkua verkossa
     * @return - palauttaa tosi, jos solmu johon on liikuttu on yksi maalisolmuista
     */
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
                return true;
            }

        }
        return false;
    }

}
