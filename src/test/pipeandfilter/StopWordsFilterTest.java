package pipeandfilter;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class StopWordsFilterTest {
    @Test
    public void canFilterStopWords() throws IOException {
        MockFilter mockFilter = new MockFilter(Arrays.asList("of", "the", "ocean"));
        StopWordsFilter g = new StopWordsFilter(mockFilter);
        List<String> output = g.run();
        List<String> expectedOutput = Collections.singletonList("ocean");
        assertArrayEquals(expectedOutput.toArray(), output.toArray());
    }
}
