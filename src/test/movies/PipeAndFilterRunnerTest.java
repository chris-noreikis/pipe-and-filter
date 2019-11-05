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
    public void canReadInputFile() throws IOException {
        assertThat(DataSource.getFileContents("/Users/chris/workspace/textprocessing/inputs/foobar.txt"), hasItems("foo", "bar"));
    }

    @Test
    public void canProcessStopWords() throws IOException {
        assertThat(StopWords.applyStopWords(Arrays.asList("foo", "bar", "ah")), hasItems("foo", "bar"));
    }

    @Test
    public void canTransformIntoRoot() throws IOException {
        List<String> expected = Arrays.asList("jump", "jump", "jump");
        assertArrayEquals(expected.toArray(), Stemming.transformIntoRoot(Arrays.asList("jump",  "jumping", "jumped")).toArray());
    }

    @Test
    public void canTransformAlice() throws IOException {
        List<String> expected = Arrays.asList("alic");
        assertArrayEquals(expected.toArray(), Stemming.transformIntoRoot(Arrays.asList("alice")).toArray());
    }

    @Test
    public void canCountSingleWord() throws IOException {
        List<String> input = Arrays.asList("jump");
        int expectedSize = 1;
        assertEquals(expectedSize, CountWords.getMostFrequentlyOccurringWordCount(input).get(0).getWordCount());
    }

    @Test
    public void canCountTwoWords() throws IOException {
        List<String> input = Arrays.asList("jump", "jump");
        int expectedJumpCount = 2;
        assertEquals(expectedJumpCount, CountWords.getMostFrequentlyOccurringWordCount(input).get(0).getWordCount());
    }

    @Test
    public void onlyReturnsTenResultsMax() throws IOException {
        List<WordCount> wordsWithCount = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            wordsWithCount.add(new WordCount(Integer.toString(i), i));
        }
        int expectedSize = 10;
        assertEquals(expectedSize, OrderWords.sortAndPrune(wordsWithCount).size());
    }

    @Test
    public void primarySortByWordCount() throws IOException {
        List<WordCount> wordsWithCount = new ArrayList<>();
        wordsWithCount.add(new WordCount("b", 1));
        wordsWithCount.add(new WordCount("a", 2));
        wordsWithCount.add(new WordCount("c", 3));
        assertEquals("c", OrderWords.sortAndPrune(wordsWithCount).get(0).getWord());
        assertEquals("a", OrderWords.sortAndPrune(wordsWithCount).get(1).getWord());
        assertEquals("b", OrderWords.sortAndPrune(wordsWithCount).get(2).getWord());
    }

    @Test
    public void secondarySortIsAlphabetical() throws IOException {
        List<WordCount> wordsWithCount = new ArrayList<>();
        wordsWithCount.add(new WordCount("b", 1));
        wordsWithCount.add(new WordCount("a", 1));
        wordsWithCount.add(new WordCount("c", 1));
        assertEquals("a", OrderWords.sortAndPrune(wordsWithCount).get(0).getWord());
        assertEquals("b", OrderWords.sortAndPrune(wordsWithCount).get(1).getWord());
        assertEquals("c", OrderWords.sortAndPrune(wordsWithCount).get(2).getWord());
    }

    @Test
    public void canPrintTableWithoutExplodingLoudly() throws IOException {
        List<WordCount> wordsWithCount = new ArrayList<>();
        wordsWithCount.add(new WordCount("b", 1));
        wordsWithCount.add(new WordCount("a", 2));
        wordsWithCount.add(new WordCount("c", 3));
        DataSink.printTable(wordsWithCount);
    }

    @Test
    public void canRunIntegrationTestWithoutExplodingLoudly() throws IOException {
        g.runEverything("/Users/chris/workspace/textprocessing/inputs/usdeclar.txt");
    }
}
