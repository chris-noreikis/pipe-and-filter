package movies;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
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

    @Test
    public void canReadInputFile() throws IOException {
        assertThat(g.getFileContents("foobar.txt"), hasItems("foo", "bar"));
    }

    @Test
    public void canProcessStopWords() throws IOException {
        assertThat(g.applyStopWords(Arrays.asList("foo", "bar", "ah")), hasItems("foo", "bar"));
    }
}
