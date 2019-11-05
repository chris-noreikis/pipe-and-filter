package movies;

import java.util.List;

public class DataSink {
    public static void printTable(List<WordCount> wordsWithCount) {
        String string = String.format("%20s %20s", "Word", "Count");
        System.out.println(string);
        System.out.println("------------------------------------------");

        wordsWithCount.forEach(e -> {
            String string2 = String.format("%20s %20s", e.getWord(), e.getWordCount());
            System.out.println(string2);
        });
    }
}
