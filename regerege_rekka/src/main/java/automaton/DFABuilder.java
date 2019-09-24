package automaton;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class DFABuilder {

    private int nodeId;
    private LinkedList<Node> nfa;
    private LinkedList<Node> unprocessedNodes;
    private Set<Character> inputChars;
    private Set<Node> allReadyProcessed;


    public DFABuilder(int nodeId, LinkedList<Node> nfa, Set<Character> inputChars) {
        this.nodeId = nodeId;
        this.nfa = nfa;
        this.unprocessedNodes = new LinkedList<>();
        this.inputChars = inputChars;
        this.allReadyProcessed = new HashSet<>();
    }

    public void buildDFA() {
        Node nfaStartNode = nfa.get(0);
        Set<Node> nfaStarNodes = new HashSet<>();
        nfaStarNodes.add(nfaStartNode);

        Set<Node> dfaStarNodes = epsilonTransfers(nfaStarNodes);
        Node dfaStartNode = new Node(++nodeId,dfaStarNodes);

        LinkedList<Node> dfaA = new LinkedList<>();
        Stack<Node> unprocessedDFANodes = new Stack<>();
        dfaA.add(dfaStartNode);
        unprocessedDFANodes.add(dfaStartNode);

        allReadyProcessed.add(dfaStartNode);

        while (!unprocessedDFANodes.empty()) {
            Node curr = unprocessedDFANodes.pop();

            for (char c : inputChars) {
                Set<Node> nextDFANode = transferWhitChar(c, curr.getNfaNodeSet());
                nextDFANode = epsilonTransfers(nextDFANode);

                /////// fix this
                boolean found = false;
                Node allReadyAtDFA = curr;
                for (int i = 0; i < dfaA.size(); i++) {
                    allReadyAtDFA = dfaA.get(i);
                    if ( nextDFANode == allReadyAtDFA.getNfaNodeSet()) {
                        found = true;
                        break;
                    }
                }
                ///////

                if (!found) {
                    Node newDFANode = new Node(++nodeId, nextDFANode);
                    unprocessedDFANodes.push(newDFANode);
                    allReadyProcessed.add(newDFANode);
                    curr.addTransfer(newDFANode, c);
                } else {
                    curr.addTransfer(allReadyAtDFA, c);
                }
                /*if (!allReadyProcessed.contains(nextDFANode)) {
                    Node newDFANode = new Node(++nodeId, nextDFANode);
                    unprocessedDFANodes.push(newDFANode);
                    allReadyProcessed.add(newDFANode);
                    curr.addTransfer(newDFANode, c);
                } else {

                }*/
            }

        }

    }

    //public void process

    private Set<Node> epsilonTransfers(Set<Node> nodeSet) {
        Stack<Node> nodesToProcess = new Stack<>();
        Set<Node> result = nodeSet;
        for (Node n : nodeSet) {
            nodesToProcess.push(n);
        }

        while (!nodesToProcess.empty()) {
            System.out.println(nodesToProcess.size());
            Node curr = nodesToProcess.pop();
            System.out.println(curr.getId());
            if (curr.getNfaTransfers().containsKey('#')) {
                for (Node next : curr.getNfaTransfers().get('#')) {
                    //nodesToProcess.push(next);
                    if (!result.contains(next)) {
                        result.add(next);
                        nodesToProcess.push(next);
                    }
                }
            }

        }
        return result;
    }

    private Set<Node> transferWhitChar(char inputCh, Set<Node> nodeSet) {
        Set<Node> result = new HashSet<>();
        for (Node n : nodeSet) {

            for (Node next : n.getNfaTransfers().get(inputCh)) {
                result.add(next);
            }
        }
        return result;
    }

}
