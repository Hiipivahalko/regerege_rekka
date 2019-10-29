package process;

import automaton.DFABuilder;
import automaton.NFABuilder;
import automaton.node.Node;
import dataStructures.MList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;

public class FinderTest {

    private Node start;
    private Process process;
    private String regex1 = "tämä";
    private String regex2 = "[A-C]testi";
    private String regex3 = "ab(ab)*aa";
    private Finder finder;
    static MList<String> testLines = new MList<>();
    static String testFile = "src/main/resources/testfile.txt";

    /**
     * Tallennetaan testitiedoston rivit listaan, ennen kaikkia muita testejä
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        try (BufferedReader bf = new BufferedReader(new FileReader(testFile))){
            String line;
            while ((line = bf.readLine()) != null) {
                testLines.add(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Before
    public void setUp() throws Exception {
        //String[] args = new String[]{testFile};
        String[] args = new String[]{regex1,testFile};
        this.process = new Process(args);
    }


    /**
     * Testataan löytääkö finder osamerkkijonon annetuista merkkijoista,
     * ilman star, union tai tyhjän merkkijonon operaatioita
     */
    @Test
    public void findSubstring1() {
        start = process.buildAutomaton(regex1);
        finder = new Finder(start);

        String line = "testingtämätesting";
        String line2 = "tämä";
        String line3 = "testingtämä";
        String line4 = "tama";
        String line5 = "jootäma";

        assertTrue(finder.findSubstring(line) && finder.findSubstring(line2)
                && finder.findSubstring(line3));

        assertTrue(!finder.findSubstring(line4) && !finder.findSubstring(line5));
    }

    /**
     * Monta pientä testiä, kun säännöllisessä lausekkeessa on käytetty union operaatiota
     */
    @Test
    public void findSubstring2() {
        start = process.buildAutomaton(regex2);

        finder = new Finder(start);

        String good1 = "Atesti";
        String good2 = "Btesti";
        String good3 = "Ctesti";
        String good4 = "jee Ctesti";

        String bad1 = "Dtesti";
        String bad2 = "testi";
        String bad3 = "atesti";
        String bad4 = "testiA";
        String bad5 = "Btest";

        // hyväksyty rivit
        assertTrue(finder.findSubstring(good1));
        assertTrue(finder.findSubstring(good2));
        assertTrue(finder.findSubstring(good3));
        assertTrue(finder.findSubstring(good4));

        // ei hyväksytyt rivit
        assertTrue(!finder.findSubstring(bad1));
        assertTrue(!finder.findSubstring(bad2));
        assertTrue(!finder.findSubstring(bad3));
        assertTrue(!finder.findSubstring(bad4));
        assertTrue(!finder.findSubstring(bad5));
    }

    /**
     * Testataan että star operaation sisältävä säännöllinen lauseke
     * löytää sopivat merkkijonot
     */
    @Test
    public void findSubstring3() {
        start = process.buildAutomaton(regex3);

        finder = new Finder(start);
        String good1 = "abaa";
        String good2 = "ababaa";
        String good3 = "jeeabaa";
        String good4 = "ababababababababababababababababababaa";
        String good5 = "abaaaaaaaaaa";
        String bad1 = "baa";
        String bad2 = "aab";
        String bad3 = "ab";
        String bad4 = "jeeiieörogjaerögioajererögioaerjgaöioej";
        String bad5 = "aaaaaaaaaaaaaaaaaaaaaa   aaaaaaaa b a";
        String bad6 = "a baa";
        String bad7 = "aa";

        assertTrue(finder.findSubstring(good1) && finder.findSubstring(good2) &&
                finder.findSubstring(good3) && finder.findSubstring(good4) &&
                finder.findSubstring(good5));

        assertTrue(!finder.findSubstring(bad1));
        assertTrue(!finder.findSubstring(bad2));
        assertTrue(!finder.findSubstring(bad3));
        assertTrue(!finder.findSubstring(bad4));
        assertTrue(!finder.findSubstring(bad5));
        assertTrue(!finder.findSubstring(bad6));
        assertTrue(!finder.findSubstring(bad7));
    }

    /**
     * Testataan sitä että mertkkijonon sisällä kahden "rakenteen" sisällä voi olla mitä vain
     */
    @Test
    public void findSubstring4() {
        start = process.buildAutomaton("joo.*joo");
        finder = new Finder(start);

        String good1 = "joojoo";
        String good2 = "joo joo";
        String good3 = "joo mfewpiofmaweöpfiomawöefiomaweöäfmieäfmefäpomepä joo";
        String good4 = "joo tähän voi laittaa mitä vain joo";
        String good5 = "joo aaaaaaaaaaaaaa joo";
        String bad1 = "joo";
        String bad2 = "joojo";
        String bad3 = "joogsergsmerägosergmospermgäe";
        String bad4 = "jeeiieörogjaerögioajererögioaerjgaöioej";
        String bad5 = "jo ojoo";
        String bad6 = "oojooj";
        String bad7 = "jojo";

        assertTrue(finder.findSubstring(good1) && finder.findSubstring(good2) &&
                finder.findSubstring(good3) && finder.findSubstring(good4) &&
                finder.findSubstring(good5));

        assertTrue(!finder.findSubstring(bad1));
        assertTrue(!finder.findSubstring(bad2));
        assertTrue(!finder.findSubstring(bad3));
        assertTrue(!finder.findSubstring(bad4));
        assertTrue(!finder.findSubstring(bad5));
        assertTrue(!finder.findSubstring(bad6));
        assertTrue(!finder.findSubstring(bad7));
    }

    /**
     * Testataan että tiettyyn kohtaan merkkijonoa voi laittaa vain yhden minkä tahansa merkin,
     * mutta ei monta
     */
    @Test
    public void findSubstring5() {
        String regex = "test.test";
        start = process.buildAutomaton(regex);

        Finder finder = new Finder(start);

        for (int i = 'a'; i <= 'z'; i++) {
            assertTrue(finder.findSubstring("test" + (char) i + "test"));
        }

        assertTrue(!finder.findSubstring("test tähän jotain väliin test"));
        assertTrue(!finder.findSubstring("testtest"));
    }

    /**
     * Testataan että union operaatio toimii kahdelle merkkijonolle,
     * eli esim. 'aaaaa...|bbbb...'
     */
    @Test
    public void findSubstring6() {
        String regex = "moi|testi";
        start = process.buildAutomaton(regex);
        Finder finder = new Finder(start);
        String good1 = "moi";
        String good2 = "testi";
        String bad1 = "mo";
        String bad2 = "test";
        String bad3 = "oi";
        assertTrue(finder.findSubstring(good1));
        assertTrue(finder.findSubstring(good2));
        assertTrue(!finder.findSubstring(bad1));
        assertTrue(!finder.findSubstring(bad2));
        assertTrue(!finder.findSubstring(bad3));

    }

}