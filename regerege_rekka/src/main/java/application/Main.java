package application;

import automaton.DFABuilder;
import automaton.NFABuilder;
import automaton.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("Jou");
        System.out.println("Welcome to program\n------------------");

        //String myRegex = "*test*";
        String myRegex = "t(es)*t*";
        //String myRegex = "aa|b";
        System.out.println("regex: " + myRegex);
        NFABuilder nfaBuilder = new NFABuilder(myRegex);
        nfaBuilder.build();

        //DFABuilder dfaBuilder = new DFABuilder(nfaBuilder.getNodeId(), nfaBuilder.getFinalNfa(), nfaBuilder.getInputChars());
        //dfaBuilder.buildDFA();

        displayNFA(nfaBuilder.getFinalNfa());

        System.out.println("------------");

        try (BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/testfile.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());

        }
    }


    public static void displayNFA(LinkedList<Node> mNfa) {

        for (Node n : mNfa) {
            if (n.isGoalNode()) System.out.println("goal " + n.getId());
            /*for (Transfer m : n.getNfaTransfers()) {
                System.out.println(n.getId() + " --> " + m.getTo().getId() + " : " + m.getSymbol());
            }*/
            //System.out.println(n.getId() + ": " + n.getNfaTransfers());
            //System.out.println(n.getId());
            for (int i : n.getNfaTransfers().keySet()) {
                //System.out.println("char:" + (char) i);
                for (Node n2 : n.getNfaTransfers().get(i)) {
                    System.out.println(n.getId() + " --> " + n2.getId() + " : " + (char) i);
                }
            }
        }
    }



    //////////////////////////////////////////////////////////



    public static void DFABuilder() {

    }


}
