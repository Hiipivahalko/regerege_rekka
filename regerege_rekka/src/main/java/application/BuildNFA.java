package application;

import nfa.Nfa;

import java.util.Stack;

public class BuildNFA {

    private Nfa nfa;
    private int nextState;
    //private int nextState;
    private String regex;
    private int finalState;
    private Stack<Character> groupChecker;

    public BuildNFA(String regex) {
        this.nfa = new Nfa();
        this.regex = regex;
        this.nextState = 1;
        this.finalState = 0;
        this.groupChecker = new Stack<>();
    }

    /**
     * Build metodi rakentaa NFA-automaatin annetusta "regex"-lausekkeesta rekursiivisesti
     * @param regex - parsittava säännöllinenlauseke
     * @param state - sen hetkinen automaatin tila mistä lähdetään jatkamaan rakentamista
     */
    public void build(String regex, int state) {
        //System.out.println("regex: " + regex);
        int n = regex.length();
        if ( n == 0 ) return;
        if ( regex.charAt(0) == '*') {
            nfa.addTransfer(state, state, '#');
            build(regex.substring(1), state);
        } else if (n > 3 && regex.charAt(0) == '(') {
            String group = findGroup(regex);
            //System.out.println(group);
            nfa.addTransfer(state, state, '#');
            //System.out.println(regex.substring(1, group.length()+1));
            System.out.println(regex.substring(group.length()+3));
            build(regex.substring(1, group.length()), state);


        } else if (regex.charAt(0) == '[') {
            //char[] temp = new char[]
            //build(regex.substring(1, ), state);
        } else {
            nextState++;
            nfa.addTransfer(state, nextState, regex.charAt(0));
            build(regex.substring(1), nextState);
        }
    }


    private String findGroup(String regex) {
        groupChecker.clear();
        StringBuilder st = new StringBuilder();
        groupChecker.push(regex.charAt(0));
        int endIdx = 0;
        for (int i = 1; i < regex.length() && !groupChecker.empty(); i++) {
            char c = regex.charAt(i);
            //st.append(c);
            if (c == ')') {
                groupChecker.pop();
                endIdx = i;
            } else if (c == '(') groupChecker.push(c);

        }
        st.append(regex.substring(1, endIdx));
        //System.out.println("group: " + st.toString());
        //System.exit(0);
        return st.toString();
    }


    public Nfa getNfa() {
        return nfa;
    }

    public int getFinalState() {
        return finalState;
    }
}
