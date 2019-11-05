package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PipeAndFilterRunner {
    public int runPipeAndFilter() {
        return 1;
    }

    public List<String> getFileContents(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        List<String> foo = Files.readAllLines(path).stream().flatMap(e -> {
            return Arrays.stream(e.replaceAll("[^A-Za-z\\s+]", "").toLowerCase().split("\\s+"));
        }).filter(e -> e.length() > 0).collect(Collectors.toList());
        System.out.println(foo);
        return foo;
    }

    public List<String> applyStopWords(List<String> input) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/chris/workspace/textprocessing/inputs/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
        return input.stream().filter(e -> !stopwords.contains(e)).collect(Collectors.toList());
    }

    public List<String> transformIntoRoot(List<String> input) throws IOException {
        return input
                .stream()
                .map(e -> {
                    Stemmer s = new Stemmer();
                    s.add(e.toCharArray(), e.length());
                    s.stem();
                    return s.toString();
                })
                .collect(Collectors.toList());
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

    public void runEverything(String filePath) throws IOException {
        List<String> fileContents = this.getFileContents(filePath);
        List<String> filteredStopWords = this.applyStopWords(fileContents);
        List<String> withDupsRemoved = this.transformIntoRoot(filteredStopWords);
        HashMap<String, Integer> withCount = this.getMostFrequentlyOccurringWordCount(withDupsRemoved);
        List<WordCount> mapped = this.mapToWordAndCount(withCount);
        List<WordCount> sorted = sortAndPrune(mapped);
        printTable(sorted);
    }
}
