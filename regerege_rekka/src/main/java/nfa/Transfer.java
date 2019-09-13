package nfa;

public class Transfer {
    private int startNode;
    private int toNode;
    private char symbol;

    public Transfer(int startNode, int toNode, char symbol) {
        this.startNode = startNode;
        this.toNode = toNode;
        this.symbol = symbol;
    }

    public int getStartNode() {
        return startNode;
    }

    public int getToNode() {
        return toNode;
    }

    public char getSymbol() {
        return symbol;
    }
}
