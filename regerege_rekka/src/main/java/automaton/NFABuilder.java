package automaton;

import automaton.node.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class NFABuilder {

    private int nodeId;
    private Set<Character> inputChars;
    private Stack<LinkedList<Node>> nfaStack;
    private Stack<Character> operatorsStack;
    private char epsilon;

    public NFABuilder() {
        this.nodeId = -1;
        this.inputChars = new HashSet<>();
        this.nfaStack = new Stack<>();
        this.operatorsStack = new Stack<>();
        this.epsilon = '#';
    }

    public int getNodeId() {
        return nodeId;
    }

    public Set<Character> getInputChars() {
        return inputChars;
    }

    public Stack<LinkedList<Node>> getNfaStack() {
        return nfaStack;
    }

    public Stack<Character> getOperatorsStack() {
        return operatorsStack;
    }


    // Setterit

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setInputChars(Set<Character> inputChars) {
        this.inputChars = inputChars;
    }

    public void setNfaStack(Stack<LinkedList<Node>> nfaStack) {
        this.nfaStack = nfaStack;
    }

    public void setOperatorsStack(Stack<Character> operatorsStack) {
        this.operatorsStack = operatorsStack;
    }

    ///////


    /**
     * Build metodi rakentaa NFA-automaatin annetusta "regex"-lausekkeesta itratiivisesti
     */
    public Node build(String regex) {
        regex = preprocess(regex);
        for (int i = 0; i < regex.length(); i++) {
            parseRegex(i, regex.charAt(i));
        }

        //Toteuta loput operaattori komennot pinosta
        while (!operatorsStack.empty()) {
            operate();
        }

        LinkedList<Node> finalNFA = nfaStack.pop();
        finalNFA.get(finalNFA.size()-1).setGoalNode(true);
        return finalNFA.get(0);
    }


    public void parseRegex(int index, char next) {

        if (!checkIfOperator(next)) {
            push(next);
        } else if (operatorsStack.isEmpty()) {
            operatorsStack.push(next);
        } else if (next == '(') {
            operatorsStack.push(next);
        } else if (next == ')') {
            while (operatorsStack.peek() != '(') {
                operate();
            }
        } else {
            while (!operatorsStack.empty() && precedence(next, operatorsStack.peek())) {
                operate();
            }
            operatorsStack.push(next);
        }
    }


    /**
     * Tarkistetaan onko annettu merkki joku erikoismerkeistä
     * @param c
     * @return
     */
    public boolean checkIfOperator(char c) {
        return (c == '(' || c == ')' || c == '*' || c == '|' || c == '?');
    }

    /**
     * Esitkäsittelee regex lauseen siten että lisää tarvittavat konkatenaatio merkit lauseen sisään
     * @param regex - säännöllinen lauseke
     * @return - palauttaa muokatun regex merkkijonon
     */
    public String preprocess(String regex) {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < regex.length()-1; i++) {
            char left = regex.charAt(i);
            char right = regex.charAt(i+1);

            st.append(left);
            if (!checkIfOperator(left) || left == ')' || left == '*') {
                if (!checkIfOperator(right) || right == '(') {
                    st.append('?');
                }
            }
        }
        st.append(regex.charAt(regex.length()-1));
        return st.toString();
    }

    /**
     * Suorittaa erikoismerkkipinon päällimmäisen operaation (concat,union tai star)
     */
    public void operate() {
        if (!operatorsStack.empty()) {
            char operator = operatorsStack.pop();

            switch (operator) {
                case '?':
                    concat();
                    break;
                case 124:
                    union();
                    break;
                case 42:
                    star();
                    break;
            }
        }
    }

    /**
     * Luo automaattiin kaksi uutta tilaa a ja b, jonka jälkeen a --> b pääsee symbolilla c
     * @param c tilasiirtymän suorittava merkki
     */
    public void push(char c) {
        Node a = new Node(++nodeId);
        Node b = new Node(++nodeId);

        a.addTransfer(b, c);

        LinkedList<Node> nfaTable = new LinkedList<>();
        nfaTable.addLast(a);
        nfaTable.addLast(b);

        nfaStack.push(nfaTable);

        inputChars.add(c);
    }

    /**
     * Yhdistää kaksi automaattia yhteen (esim jonon "a ja b" tai "(ab) ja b")
     */
    public void concat() {
        LinkedList<Node> nfaB = nfaStack.pop();
        LinkedList<Node> nfaA = nfaStack.pop();

        nfaA.get(nfaA.size()-1).addTransfer(nfaB.get(0), epsilon);
        nfaA.addAll(nfaB);
        nfaStack.push(nfaA);
    }

    /**
     * Luo automaattin loopin jonkun merkkin tai ryhmän kohdalle (esim a* tai (ab)* )
     */
    public void star() {

        LinkedList<Node> nfaA = nfaStack.pop();

        Node start = new Node(++nodeId);
        Node end = new Node(++nodeId);

        start.addTransfer(end, epsilon);
        start.addTransfer(nfaA.get(0), epsilon);

        nfaA.get(nfaA.size()-1).addTransfer(end, epsilon);
        nfaA.get(nfaA.size()-1).addTransfer(nfaA.get(0), epsilon);

        nfaA.addFirst(start);
        nfaA.addLast(end);

        nfaStack.push(nfaA);

    }

    /**
     * Luo automaattiin haaran (a|b)
     */
    public void union() {
        LinkedList<Node> nfaB = nfaStack.pop();
        LinkedList<Node> nfaA = nfaStack.pop();
        Node start = new Node(++nodeId);
        Node end = new Node(++nodeId);

        start.addTransfer(nfaA.get(0), epsilon);
        start.addTransfer(nfaB.get(0), epsilon);

        nfaA.get(nfaA.size()-1).addTransfer(end, epsilon);
        nfaB.get(nfaB.size()-1).addTransfer(end, epsilon);

        nfaA.addFirst(start);
        nfaB.addLast(end);

        nfaA.addAll(nfaB);

        nfaStack.push(nfaA);
    }

    public static boolean precedence(char left, char right) {
        if(left == right) {
            return true;
        } else if(left == '*') {
            return false;
        } else if(right == '*') {
            return true;
        } else if(left == '?') {
            return false;
        } else if(right == '?') {
            return true;
        } else if(left == '|') {
            return false;
        }
        return true;
    }

}
