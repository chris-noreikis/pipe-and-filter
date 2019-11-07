package movies;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class StemmerFilterTest {
    private StemmerFilter g;

    @Before
    public void setUp() {
        g = new StemmerFilter();
    }

    @Test
    public void canStemWords() throws IOException {
        List<String> output = g.doWork(Arrays.asList("jump", "jumping", "jumped"));
        List<String> expectedOutput = Arrays.asList("jump", "jump", "jump");
        assertArrayEquals(expectedOutput.toArray(), output.toArray());
    }
}
