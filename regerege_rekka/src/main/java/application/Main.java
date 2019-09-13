package application;

import nfa.Nfa;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("Jou");
        System.out.println("Welcome to program\n------------------");

        //String myRegex = "*test*";
        String myRegex = "*t(ees)*t*";
        BuildNFA builder = new BuildNFA(myRegex);
        builder.build(myRegex, 1);
        Nfa nfa = builder.getNfa();

        nfa.display();

        System.out.println("\n");

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



}
