package automaton;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DFABuilderTest {

    private String regex1 = "(joo)*jee(test)*hahp*";
    private NFABuilder nfa;
    private DFABuilder dfa;

    @Before
    public void setUp() throws Exception {
        this.nfa = new NFABuilder(regex1);
        this.nfa.build();
        this.dfa = new DFABuilder(nfa.getNodeId(), nfa.getFinalNfa(), nfa.getInputChars());

    }

    @Test
    public void epsilonTransfers() {

    }
}