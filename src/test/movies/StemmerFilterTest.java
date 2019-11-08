package pipeandfilter;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class StemmerFilterTest {
    @Test
    public void canStemWords() throws IOException {
        MockFilter mockFilter = new MockFilter(Arrays.asList("jump", "jumping", "jumped"));
        PipeFilter g = g = new StemmerFilter(mockFilter);
        List<String> output = g.run();
        List<String> expectedOutput = Arrays.asList("jump", "jump", "jump");
        assertArrayEquals(expectedOutput.toArray(), output.toArray());
    }
}
