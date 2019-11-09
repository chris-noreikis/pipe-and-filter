package pipeandfilter;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class LinesToTermsFilterTest {
    @Test
    public void canParseWords() throws IOException {
        MockFilter mockFilter = new MockFilter(Arrays.asList("foo     ---      bar       bar"));
        PipeFilter linesToWordsFilter = new LinesToTermsFilter(mockFilter);
        List<String> output = linesToWordsFilter.run();
        List<String> expectedOutput = Arrays.asList("foo", "bar", "bar");
        assertArrayEquals(expectedOutput.toArray(), output.toArray());
    }
}
