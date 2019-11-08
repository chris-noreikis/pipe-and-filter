package pipeandfilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DataSink implements PipeFilter {
    private PipeFilter previousFilter;
    public DataSink(PipeFilter previousFilter) {
        this.previousFilter = previousFilter;
    }

    public List<WordCount> sortAndPrune(List<WordCount> wordsWithCount) {
        return wordsWithCount.stream().sorted((o1, o2) -> {
            if (o1.getWordCount() != o2.getWordCount()) {
                return o2.getWordCount() - o1.getWordCount();
            }

            return o1.getWord().compareTo(o2.getWord());
        }).limit(10).collect(Collectors.toList());
    }

    private List<WordCount> mapToWordAndCount(HashMap<String, Integer> wordCountsByWord) {
        return wordCountsByWord.keySet().stream().map(e -> new WordCount(e, wordCountsByWord.get(e))).collect(Collectors.toList());
    }

    public List<WordCount> getMostFrequentlyOccurringWordCount(List<String> input) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        input.forEach(word -> {
            Integer currentCount = wordCount.get(word);
            if (currentCount == null) {
                currentCount = 0;
            }

            currentCount++;
            wordCount.put(word, currentCount);
        });

        return this.mapToWordAndCount(wordCount);
    }

    public void printTable(List<String> input) {
        List<WordCount> wordsWithCount = this.getMostFrequentlyOccurringWordCount(input);
        wordsWithCount = this.sortAndPrune(wordsWithCount);

        printTableHeader();
        printTableBody(wordsWithCount);
    }

    private void printTableBody(List<WordCount> wordsWithCount) {
        wordsWithCount.forEach(e -> {
            String string2 = String.format("%35s %20s", e.getWord(), e.getWordCount());
            System.out.println(string2);
        });
    }

    private void printTableHeader() {
        String string = String.format("%35s %20s", "Word", "Count");
        System.out.println(string);
        System.out.println("------------------------------------------------------------");
    }

    @Override
    public List<String> run() throws IOException {
        this.printTable(previousFilter.run());
        return null;
    }
}
