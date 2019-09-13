package nfa;

import java.util.ArrayList;

public class Nfa {

    private ArrayList<Integer> nodes;
    private ArrayList<Transfer> trans;

    public Nfa() {
        this.nodes = new ArrayList<>();
        this.trans = new ArrayList<>();
        //lisätään alku- ja maalisolmu
        nodes.add(0);
        nodes.add(1);
    }

    /**
     * Lisää kahden tilan välille kaaren, johon päästään symbolilla @symbol
     * @param from - Lähtötila
     * @param to - kohdetila
     * @param symbol - symboli, jolla päästään liikkumaan tilojen välillä
     * @return
     */
    public int addTransfer(int from, int to, char symbol) {
        //trans.add(new Transfer(from, to, new char[] {symbol}));
        trans.add(new Transfer(from, to, symbol));
        if (symbol != '0') {
            return to++;
        }
        return to;
    }

    public ArrayList<Transfer> getTrans() {
        return trans;
    }

    public void display() {
        for (int i = 0; i < trans.size(); i++) {
            Transfer t = trans.get(i);
            System.out.println("S" + t.getStartNode() + " --> S" + t.getToNode() + " with symbol: " + t.getSymbol());
        }
    }
}
