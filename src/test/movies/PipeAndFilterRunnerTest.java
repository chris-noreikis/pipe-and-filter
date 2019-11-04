package movies;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PipeAndFilterRunnerTest {
    private PipeAndFilterRunner g;

    @Before
    public void setUp()
    {
        g = new PipeAndFilterRunner();
    }

    @Test
    public void testCanReturnHardcodedAge()
    {
        assertEquals(g.runPipeAndFilter(),1);
    }
}
