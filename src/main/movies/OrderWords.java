package movies;

import java.util.List;
import java.util.stream.Collectors;

public class OrderWords {
    public static List<WordCount> sortAndPrune(List<WordCount> wordsWithCount) {
        return wordsWithCount.stream().sorted((o1, o2) -> {
            if (o1.getWordCount() != o2.getWordCount()) {
                return o2.getWordCount() - o1.getWordCount();
            }

            return o1.getWord().compareTo(o2.getWord());
        }).limit(10).collect(Collectors.toList());
    }
}
