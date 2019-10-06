package main;

import automaton.DFABuilder;
import automaton.NFABuilder;
import debug.DisplayAutomaton;
import process.Finder;
import process.Process;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length > 1) {
            Process app = new Process(args);
            app.run();

        } else {
            System.out.println("Insert arguments to program");
            System.out.println("    gradle run --args'<regex> <path_to_file>'   or\n" +
                    "   java -jar file.jar \"<regex>\" <path_to_file>");
        }

    }






}
