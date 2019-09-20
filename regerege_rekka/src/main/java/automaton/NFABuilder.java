package automaton;

import automaton.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class NFABuilder {

    private int nodeId;
    private Set<Character> inputChars;
    private Stack<LinkedList<Node>> nfaStack;
    private Stack<Character> operatorsStack;
    private LinkedList<Node> finalNfa;
    private String regex;

    public NFABuilder(String regex) {
        this.nodeId = -1;
        this.inputChars = new HashSet<>();
        this.nfaStack = new Stack<>();
        this.operatorsStack = new Stack<>();
        this.finalNfa = new LinkedList<>();
        this.regex = regex;
    }

    public LinkedList<Node> getFinalNfa() {
        return finalNfa;
    }

    public int getNodeId() {
        return nodeId;
    }

    public Set<Character> getInputChars() {
        return inputChars;
    }

    /**
     * Build metodi rakentaa NFA-automaatin annetusta "regex"-lausekkeesta itratiivisesti
     */
    public void build() {
        regex = preprocess(regex);
        for (int i = 0; i < regex.length(); i++) {
            char next = regex.charAt(i);

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

        while (!operatorsStack.empty()) {
            operate();
        }
        finalNfa = nfaStack.pop();
        finalNfa.get(finalNfa.size()-1).setGoalNode(true);

    }

    /**
     * Tarkistetaan onko annettu merkki joku erikoismerkeistä
     * @param c
     * @return
     */
    private boolean checkIfOperator(char c) {
        return (c == '(' || c == ')' || c == '*' || c == '|' || c == '?');
    }

    /**
     * Esitkäsittelee regex lauseen siten että lisää tarvittavat konkatenaatio merkit lauseen sisään
     * @param regex - säännöllinen lauseke
     * @return - palauttaa muokatun regex merkkijonon
     */
    private String preprocess(String regex) {
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
        System.out.println(st.toString());
        return st.toString();
    }

    /**
     * Suorittaa erikoismerkkipinon päällimmäisen operaation (concat,union tai star)
     */
    private void operate() {
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
                /*default:
                    System.out.println("Error with operator: " + operator);
                    System.exit(1);*/
            }
        }
    }

    /**
     * Luo automaattiin kaksi uutta tilaa a ja b, jonka jälkeen a --> b pääsee symbolilla c
     * @param c tilasiirtymän suorittava merkki
     */
    private void push(char c) {
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
    private void concat() {
        LinkedList<Node> nfaB = nfaStack.pop();
        LinkedList<Node> nfaA = nfaStack.pop();

        nfaA.get(nfaA.size()-1).addTransfer(nfaB.get(0), '#');
        nfaA.addAll(nfaB);
        nfaStack.push(nfaA);
    }

    /**
     * Luo automaattin loopin jonkun merkkin tai ryhmän kohdalle (esim a* tai (ab)* )
     */
    private void star() {

        LinkedList<Node> nfaA = nfaStack.pop();


        Node start = new Node(++nodeId);
        Node end = new Node(++nodeId);

        start.addTransfer(end, '#');
        start.addTransfer(nfaA.get(0), '#');

        nfaA.get(nfaA.size()-1).addTransfer(end, '#');
        nfaA.get(nfaA.size()-1).addTransfer(nfaA.get(0), '#');

        nfaA.addFirst(start);
        nfaA.addLast(end);

        nfaStack.push(nfaA);

    }

    /**
     * Luo automaattiin haaran (a|b)
     */
    private void union() {
        LinkedList<Node> nfaB = nfaStack.pop();
        LinkedList<Node> nfaA = nfaStack.pop();
        Node start = new Node(++nodeId);
        Node end = new Node(++nodeId);

        start.addTransfer(nfaA.get(0), '#');
        start.addTransfer(nfaB.get(0), '#');

        nfaA.get(nfaA.size()-1).addTransfer(end, '#');
        nfaB.get(nfaB.size()-1).addTransfer(end, '#');

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
