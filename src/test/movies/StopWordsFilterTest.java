package movies;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class StopWordsFilterTest {
    private StopWordsFilter g;

    @Before
    public void setUp() {
        g = new StopWordsFilter();
    }

    @Test
    public void canFilterStopWords() throws IOException {
        List<String> output = g.doWork(Arrays.asList("of", "the", "ocean"));
        List<String> expectedOutput = Collections.singletonList("ocean");
        assertArrayEquals(expectedOutput.toArray(), output.toArray());
    }
}
