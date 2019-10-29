package process;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ProcessTest {

    private String smallFile;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

//    @Before
//    public void setUp() throws Exception {
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("testfile.txt").getFile());
//        this.smallFile = file.getAbsolutePath();
//
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @After
//    public void clean() {
//        System.setOut(null);
//        System.setErr(null);
//        outContent.reset();
//        errContent.reset();
//    }
//
//    /**
//     * Testataan että säännöllinen lauseke tuottaa oikeat rivit testitiedostosta
//     */
//    @Test
//    public void run() {
//        String[] args1 = new String[]{"Tiralabra", smallFile};
//        Process process = new Process(args1);
//        process.run();
//        String expected = "Tiralabra on kiva kurssi\n";
//        assertEquals(expected, outContent.toString());
//    }
//
//    /**
//     * Testataan että säännöllinen lauseke tuottaa oikeat rivit testitiedostosta
//     */
//    @Test
//    public void run2() {
//        String[] args1 = new String[]{"ab(ab)*aaa", smallFile};
//        Process process = new Process(args1);
//        process.run();
//        String expected = "bmaerbämeabamerb--ababababababaaaab--oaerjgäoaerjgaäjeräieojga\n" +
//                "12345678990krrjgkrgäaaäkfmgmffngng--abaaaab--mmmmmmmmmmmm\n" +
//                "abababaaabtämä\n";
//        assertEquals(expected, outContent.toString());
//    }
//
//    /**
//     * Testataan että säännöllinen lauseke tuottaa oikeat rivit testitiedostosta
//     */
//    @Test
//    public void run3() {
//        String[] args1 = new String[]{"hakasulku.*[A-DP]testi", smallFile};
//        Process process = new Process(args1);
//        process.run();
//        String expected = "tämä hakasulku  Atesti\n" +
//                "hakasulku  Btesti\n" +
//                "hakasulku  Ctesti\n" +
//                "hakasulku  Dtesti\n" +
//                "hakasulku  Ptesti\n";
//        assertEquals(expected, outContent.toString());
//    }
//
//    /**
//     * Testataan että säännöllinen lauseke tuottaa oikeat rivit testitiedostosta
//     */
//    @Test
//    public void run4() {
//        String[] args1 = new String[]{"tämä.*[A-DP]testi", smallFile};
//        Process process = new Process(args1);
//        process.run();
//        String expected = "tämä hakasulku  Atesti\n" +
//                "toinen tämäWWAtesti\n";
//        assertEquals(expected, outContent.toString());
//    }
//
//    /**
//     * Testataan että säännöllinen lauseke tuottaa oikeat rivit testitiedostosta
//     */
//    @Test
//    public void run5() {
//        String[] args1 = new String[]{"tämä..[A-DP]testi", smallFile};
//        Process process = new Process(args1);
//        process.run();
//        String expected = "toinen tämäWWAtesti\n";
//        assertEquals(expected, outContent.toString());
//    }
//
//    /**
//     * Testataan että säännöllinen lauseke tuottaa oikeat rivit testitiedostosta
//     */
//    @Test
//    public void run6() {
//        String[] args1 = new String[]{"a", smallFile};
//        Process process = new Process(args1);
//        process.run();
//
//        String expected = "Tiralabra on kiva kurssi\n" +
//                "Tira on kiva kurssi\n" +
//                "This is another test line\n" +
//                "aabbabababaabababa Tira\n" +
//                "bmaerbämeabamerb--ababababababaaaab--oaerjgäoaerjgaäjeräieojga\n" +
//                "12345678990krrjgkrgäaaäkfmgmffngng--abaaaab--mmmmmmmmmmmm\n" +
//                "ababab\n" +
//                "aaaab  tämä\n" +
//                "abababaaabtämä\n" +
//                "abbaaaab\n" +
//                "tämä hakasulku  Atesti\n" +
//                "hakasulku  Btesti\n" +
//                "hakasulku  Ctesti\n" +
//                "hakasulku  Dtesti\n" +
//                "hakasulku  Ptesti\n" +
//                "hakasulku  Rtesti\n";
//
//        assertEquals(expected, outContent.toString());
//    }
//
//    /**
//     * Testataan että säännöllinen lauseke tuottaa oikeat rivit testitiedostosta
//     */
//    @Test
//    public void run7() {
//        String[] args1 = new String[]{"hakasulku|Tira|[0-9]", smallFile};
//        Process process = new Process(args1);
//        process.run();
//        String expected = "Tiralabra on kiva kurssi\n" +
//                "Tira on kiva kurssi\n" +
//                "aabbabababaabababa Tira\n" +
//                "12345678990krrjgkrgäaaäkfmgmffngng--abaaaab--mmmmmmmmmmmm\n" +
//                "tämä hakasulku  Atesti\n" +
//                "hakasulku  Btesti\n" +
//                "hakasulku  Ctesti\n" +
//                "hakasulku  Dtesti\n" +
//                "hakasulku  Ptesti\n" +
//                "hakasulku  Rtesti\n";
//        assertEquals(expected, outContent.toString());
//    }

}