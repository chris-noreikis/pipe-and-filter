package movies;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CountWords {
    private static List<WordCount> mapToWordAndCount(HashMap<String, Integer> wordCountsByWord) {
        return wordCountsByWord.keySet().stream().map(e -> new WordCount(e, wordCountsByWord.get(e))).collect(Collectors.toList());
    }

    public static List<WordCount> getMostFrequentlyOccurringWordCount(List<String> input) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        input.forEach(word -> {
            Integer currentCount = wordCount.get(word);
            if (currentCount == null) {
                currentCount = 0;
            }

            currentCount++;
            wordCount.put(word, currentCount);
        });

        return CountWords.mapToWordAndCount(wordCount);
    }
}
