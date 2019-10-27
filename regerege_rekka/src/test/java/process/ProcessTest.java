package process;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessTest {

    String smallFile = "src/main/resource/testfile.txt";

    private Process process;
    private String[] args1 = new String[]{"ab(ab)*aaa", smallFile};

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void run() {
    }
}