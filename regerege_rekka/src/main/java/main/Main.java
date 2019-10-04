package main;

import automaton.DFABuilder;
import automaton.NFABuilder;
import automaton.Node;
import debug.DisplayAutomaton;
import process.Finder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String myRegex = "Tiralabra";
        if (args.length > 0) {
            myRegex = args[0];
            System.out.println(myRegex);
            System.exit(0);
        }
        NFABuilder nfa = new NFABuilder(myRegex);
        nfa.build();
        DisplayAutomaton da = new DisplayAutomaton();
        DFABuilder dfaBuilder = new DFABuilder(nfa.getNodeId(), nfa.getFinalNfa(), nfa.getInputChars());
        dfaBuilder.buildDFA();
        Finder finder = new Finder(dfaBuilder.getStartNode());
        try (BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/testfile.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                //System.out.println(line);
                finder.findSubstring(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());

        }
    }






}
