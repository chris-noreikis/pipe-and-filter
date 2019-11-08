package pipeandfilter;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PipeAndFilterRunnerTest {
    private PipeAndFilterRunner g;

    @Before
    public void setUp()
    {
        g = new PipeAndFilterRunner();
    }

//    @Test
//    public void canRunDeclaration() throws IOException {
//        g.runEverything("src/dist/usdeclar.txt");
//    }

    @Test
    public void canRunKjBible() throws IOException {
        g.runEverything("src/dist//kjbible.txt");
    }
}
