package automaton;

import java.util.Set;

public class Transfer {

    private Set<Node> to;
    private char symbol;

    public Transfer(Set<Node> to, char symbol) {
        this.to = to;
        this.symbol = symbol;
    }

    public Set<Node> getTo() {
        return to;
    }

    public char getSymbol() {
        return symbol;
    }
}
