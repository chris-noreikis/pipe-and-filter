package movies;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
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

    @Test
    public void canTransformIntoRoot() throws IOException {
        List<String> expected = Arrays.asList("jump", "jump", "jump");
        assertArrayEquals(expected.toArray(), g.transformIntoRoot(Arrays.asList("jump",  "jumping", "jumped")).toArray());
    }

    @Test
    public void canTransformAlice() throws IOException {
        List<String> expected = Arrays.asList("alic");
        assertArrayEquals(expected.toArray(), g.transformIntoRoot(Arrays.asList("alice")).toArray());
    }

    @Test
    public void canCountSingleWord() throws IOException {
        List<String> input = Arrays.asList("jump");
        HashMap<String, Integer> wordCount = new HashMap<>();
        wordCount.put("jump", 1);
        Integer expectedJumpCount = 1;
        assertEquals(expectedJumpCount, g.getMostFrequentlyOccurringWordCount(input).get("jump"));
    }

    @Test
    public void canCountTwoWords() throws IOException {
        List<String> input = Arrays.asList("jump", "jump");
        Integer expectedJumpCount = 2;
        assertEquals(expectedJumpCount, g.getMostFrequentlyOccurringWordCount(input).get("jump"));
    }

    @Test
    public void onlyReturnsTenResultsMax() throws IOException {
        ArrayList<String> input = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            input.add(Integer.toString(i));
        }
        int expectedSize = 10;
        assertEquals(expectedSize, g.getMostFrequentlyOccurringWordCount(input).size());
    }
}
