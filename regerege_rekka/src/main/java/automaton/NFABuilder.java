package automaton;

import automaton.node.Node;
import dataStructures.MLinkedList;
import dataStructures.Mstack;

import java.util.HashSet;
import java.util.Set;

public class NFABuilder {

    private int nodeId;
    private Set<Character> inputChars;
    private char epsilon;
    private char allChars;
    private char concatChar;

    private Mstack<MLinkedList<Node>> nfaStack;
    private Mstack<Character> operatorsStack;

    public NFABuilder() {
        this.nodeId = -1;
        this.inputChars = new HashSet<>();
        this.epsilon = 0;
        this.allChars = 1;
        this.concatChar = 8;

        this.nfaStack = new Mstack<>();
        this.operatorsStack = new Mstack<>();
    }

    public int getNodeId() {
        return nodeId;
    }

    public Set<Character> getInputChars() {
        return inputChars;
    }

    public Mstack<MLinkedList<Node>> getNfaStack() {
        return nfaStack;
    }

    public Mstack<Character> getOperatorsStack() {
        return operatorsStack;
    }


    // Setterit

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setInputChars(Set<Character> inputChars) {
        this.inputChars = inputChars;
    }

    public void setNfaStack(Mstack<MLinkedList<Node>> nfaStack) {
        this.nfaStack = nfaStack;
    }

    public void setOperatorsStack(Mstack<Character> operatorsStack) {
        this.operatorsStack = operatorsStack;
    }

    ///////


    /**
     * Build metodi rakentaa NFA-automaatin annetusta "regex"-lausekkeesta itratiivisesti
     */
    public Node build(String regex) {
        regex = preprocess(regex);
        for (int i = 0; i < regex.length(); i++) {
            if (parseRegex(i, regex)) i++;
        }

        //Toteuta loput operaattori komennot pinosta
        while (!operatorsStack.empty()) {
            operate();
        }

        MLinkedList<Node> finalNFA = nfaStack.pop();
        //finalNFA.get(finalNFA.size()-1).setGoalNode(true);
        finalNFA.getLastKey().setGoalNode(true);
        return finalNFA.getFirstKey();
    }


    public boolean parseRegex(int index, String regex) {
        char next = regex.charAt(index);
        if (next == '\\') {
            if (index+1 < regex.length()) {
                push(regex.charAt(index+1));
                return true;
            } else {
                push(next);
                return false;
            }
        } else if (next == '.') {
            push(allChars);
        } else if (!checkIfOperator(next)) {
            push(next);
        } else if (operatorsStack.empty()) {
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
        return false;
    }


    /**
     * Tarkistetaan onko annettu merkki joku erikoismerkeistä
     * @param c
     * @return
     */
    public boolean checkIfOperator(char c) {
        return (c == '(' ||
                c == ')' ||
                c == '*' ||
                c == '|' ||
                c == concatChar);
    }

    /**
     * Esitkäsittelee regex lauseen siten että lisää tarvittavat konkatenaatio merkit lauseen sisään
     * @param regex - säännöllinen lauseke
     * @return - palauttaa muokatun regex merkkijonon
     */
    public String preprocess(String regex) {
        StringBuilder st = new StringBuilder();
        regex = breakBrackets(regex);
        for (int i = 0; i < regex.length()-1; i++) {
            char left = regex.charAt(i);
            char right = regex.charAt(i+1);

            st.append(left);
            if (left == '\\') {
                st.append(right);
                st.append(concatChar);
                i++;
            } else if (!checkIfOperator(left) || left == ')' || left == '*') {
                if (!checkIfOperator(right) || right == '(') {
                    st.append(concatChar);
                }
            }
        }
        st.append(regex.charAt(regex.length()-1));
        return st.toString();
    }

    /**
     * Poistaa hakasulut ja muuntaa ne yhdisteiksi säännöllisestä lausekkeesta
     * esim [abc] -> (a|b|c)
     * @param regex - säännöllinen lauseke
     * @return - muokatun säännöllisenlausekkeen (merkkijono)
     */
    public String breakBrackets(String regex) {
        StringBuilder st = new StringBuilder();
        boolean inBracket = false;
        for (int i = 0 ; i < regex.length(); i++) {
            char next = regex.charAt(i);
            if (next == '[') {
                st.append('(');
                inBracket = true;
            } else if (next == ']') {
                st.deleteCharAt(st.length()-1);
                st.append(')');
                inBracket = false;
            } else if (inBracket) {
                if (next == '\\') {
                    handleEscapeCharInBracket(st, regex, i);
                    i++;
                } else if (next == '-') {
                    st = handleLineSymbolInBracket(st, regex, i);
                    i++;
                } else {
                    st.append(next);
                    st.append('|');
                }
            } else st.append(next);
        }
        return st.toString();
    }

    /**
     * Käsittelee hakasulkujen viiva operaation eli jos hakasulkujen sisällä on
     * [a-d], niin funktio käsittelee tämän ja tekee siitä (a|b|c|d)
     * @param st
     * @param regex
     * @param i
     * @return
     */
    private StringBuilder handleLineSymbolInBracket(StringBuilder st, String regex, int i) {
        if (i > 0 && i+1 < regex.length() &&
                regex.charAt(i+1) != ']') {
            if (regex.charAt(i-1) < regex.charAt(i+1)) {
                int startSymbol = (int) regex.charAt(i-1) +1;
                for (int j = startSymbol; j <= regex.charAt(i+1); j++) {
                    st.append((char) j);
                    st.append('|');
                }
            }
        }
        return st;
    }

    /**
     * Käsittelee hakasulkujen sisällä olvena escape merkin
     * @param st - StringBuilder joka esikäittelee säännllistälauseketta
     * @param regex - alkuperäinen säännöllinen lauseke
     * @param i - alkuperäisen säännöllisen lausekkeen symboli indeksi
     * @return - StringBuilder johon on lisätty escape merkattu symboli
     */
    private StringBuilder handleEscapeCharInBracket(StringBuilder st, String regex ,int i) {
        if (i+1 < regex.length()) {
            st.append(regex.charAt(i+1));
            st.append('|');
        } else {
            System.out.println("syntax eror with regex");
            System.exit(1);
        }
        return st;
    }

    /**
     * Suorittaa erikoismerkkipinon päällimmäisen operaation (concat,union tai star)
     */
    public void operate() {
        if (!operatorsStack.empty()) {
            char operator = operatorsStack.pop();

            switch (operator) {
                // 8 == concatChar
                case 8:
                    concat();
                    break;
                    //124 == unionChar
                case 124:
                    union();
                    break;
                    // 42== starChar
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

        MLinkedList<Node> nfaTable = new MLinkedList<>();
        nfaTable.addLast(a);
        nfaTable.addLast(b);

        nfaStack.push(nfaTable);

        inputChars.add(c);
    }

    /**
     * Yhdistää kaksi automaattia yhteen (esim jonon "a ja b" tai "(ab) ja b")
     */
    public void concat() {
        MLinkedList<Node> nfaB = nfaStack.pop();
        MLinkedList<Node> nfaA = nfaStack.pop();

        nfaA.getLastKey().addTransfer(nfaB.getFirstKey(), epsilon);
        nfaA.addAll(nfaB);
        nfaStack.push(nfaA);
    }

    /**
     * Luo automaattin loopin jonkun merkkin tai ryhmän kohdalle (esim a* tai (ab)* )
     */
    public void star() {

        MLinkedList<Node> nfaA = nfaStack.pop();

        Node start = new Node(++nodeId);
        Node end = new Node(++nodeId);

        start.addTransfer(end, epsilon);
        start.addTransfer(nfaA.getFirstKey(), epsilon);

        nfaA.getLastKey().addTransfer(end, epsilon);
        nfaA.getLastKey().addTransfer(nfaA.getFirstKey(), epsilon);

        nfaA.addFirst(start);
        nfaA.addLast(end);

        nfaStack.push(nfaA);

    }

    /**
     * Luo automaattiin haaran (a|b)
     */
    public void union() {
        MLinkedList<Node> nfaB = nfaStack.pop();
        MLinkedList<Node> nfaA = nfaStack.pop();
        Node start = new Node(++nodeId);
        Node end = new Node(++nodeId);

        start.addTransfer(nfaA.getFirstKey(), epsilon);
        start.addTransfer(nfaB.getFirstKey(), epsilon);

        nfaA.getLastKey().addTransfer(end, epsilon);
        nfaB.getLastKey().addTransfer(end, epsilon);

        nfaA.addFirst(start);
        nfaB.addLast(end);

        nfaA.addAll(nfaB);

        nfaStack.push(nfaA);
    }

    public boolean precedence(char left, char right) {
        if(left == right) {
            return true;
        } else if(left == '*') {
            return false;
        } else if(right == '*') {
            return true;
        } else if(left == concatChar) {
            return false;
        } else if(right == concatChar) {
            return true;
        } else if(left == '|') {
            return false;
        }
        return true;
    }

}
