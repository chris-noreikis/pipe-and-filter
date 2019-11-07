package movies;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class LinesToWordsFilterTest {
    private LinesToWordsFilter g;

    @Before
    public void setUp() {
        g = new LinesToWordsFilter();
    }

    @Test
    public void canParseWords() throws IOException {
        List<String> output = g.doWork(Arrays.asList("foo     ---      bar       bar"));
        List<String> expectedOutput = Arrays.asList("foo", "bar", "bar");
        assertArrayEquals(expectedOutput.toArray(), output.toArray());
    }
}