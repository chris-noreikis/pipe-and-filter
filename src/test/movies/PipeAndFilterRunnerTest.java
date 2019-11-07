package movies;

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

    @Test
    public void canRunDeclaration() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        g.runEverything("dist/usdeclar.txt");
    }

    @Test
    public void canRunKjBible() throws IOException {
        g.runEverything("dist/kjbible.txt");
    }
}
