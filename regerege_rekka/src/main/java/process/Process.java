package process;

import automaton.DFABuilder;
import automaton.NFABuilder;
import automaton.node.Node;
import debug.DisplayAutomaton;

import java.io.BufferedReader;
import java.io.FileReader;

public class Process {

    private String[] args;
    private Node startNode;

    public Process(String args[]) {
        this.args = args;
        this.startNode = buildAutomaton(args[0]);
    }

    /**
     * Etsii annetusta tiedostosta yhtenäisiä osamerkkijonoja,
     * jotka sisältyy annettuun säännölliseen lausekkeeseen
     */
    public void run() {
        Finder finder = new Finder(startNode);
        for (int i = 1; i < args.length; i++) {
            int row = 1;
            try (BufferedReader bf = new BufferedReader(new FileReader(args[i]))) {
                String line;
                while ((line = bf.readLine()) != null) {
                    if (finder.findSubstring(line)) System.out.println(row + ". :" + line);
                    //System.out.println(line);
                    row++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                System.exit(1);

            }
        }

    }

    /**
     * Rakentaa NFA- ja DFA-verkon
     * @param regex - säännöllinen lauseke
     * @return - palauttaa DFA-verkon aloitussollmun
     */
    public Node buildAutomaton(String regex) {
        NFABuilder nfa = new NFABuilder();
        Node nfaStartNode = nfa.build(regex);

        DisplayAutomaton da = new DisplayAutomaton();
        DFABuilder dfaBuilder = new DFABuilder(nfa.getNodeId(), nfa.getInputChars());
        return dfaBuilder.buildDFA(nfaStartNode);
    }


}
