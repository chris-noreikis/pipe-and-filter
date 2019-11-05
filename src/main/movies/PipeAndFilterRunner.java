package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

class WordCount {
    private int wordCount;
    private String word;

    WordCount(String word, Integer wordCount) {
        this.wordCount = wordCount;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getWordCount() {
        return wordCount;
    }
}

public class PipeAndFilterRunner {
    public int runPipeAndFilter() {
        return 1;
    }

    public List<String> getFileContents(String fileName) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/chris/workspace/textprocessing/inputs/foobar.txt");
        return Files.readAllLines(path);
    }

    public List<String> applyStopWords(List<String> input) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/chris/workspace/textprocessing/inputs/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
        return input.stream().filter(e -> !stopwords.contains(e)).collect(Collectors.toList());
    }

    public List<String> removeDupList(List<String> list, boolean ignoreCase) {
        Set<String> set = (ignoreCase ? new TreeSet<String>(String.CASE_INSENSITIVE_ORDER) : new LinkedHashSet<String>());
        set.addAll(list);

        List<String> res = new ArrayList<String>(set);
        return res;
    }

    public List<String> transformIntoRoot(List<String> input) throws IOException {
        List<String> prelimInput = input
                .stream()
                .map(e -> {
                    Stemmer s = new Stemmer();
                    s.add(e.toCharArray(), e.length());
                    s.stem();
                    return s.toString();
                })
                .collect(Collectors.toList());
        return prelimInput;
    }

    public HashMap<String, Integer> getMostFrequentlyOccurringWordCount(List<String> input) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        input.forEach(word -> {
            Integer currentCount = wordCount.get(word);
            if (currentCount == null) {
                currentCount = 0;
            }

            currentCount++;
            wordCount.put(word, currentCount);
        });

        return wordCount;
    }

    public List<WordCount> mapToWordAndCount(HashMap<String, Integer> wordCountsByWord) {
        return wordCountsByWord.keySet().stream().map(e -> new WordCount(e, wordCountsByWord.get(e))).collect(Collectors.toList());
    }

    public List<WordCount> sortAndPrune(List<WordCount> wordsWithCount) {
        return wordsWithCount.stream().sorted((o1, o2) -> {
            if (o1.getWordCount() != o2.getWordCount()) {
                return o2.getWordCount() - o1.getWordCount();
            }

            return o1.getWord().compareTo(o2.getWord());
        }).limit(10).collect(Collectors.toList());
    }

    public void printTable(List<WordCount> wordsWithCount) {
        String string = String.format("%20s %20s", "Word", "Count");
        System.out.println(string);
        System.out.println("------------------------------------------");

        wordsWithCount.forEach(e -> {
            String string2 = String.format("%20s %20s", e.getWord(), e.getWordCount());
            System.out.println(string2);
        });
    }
}
