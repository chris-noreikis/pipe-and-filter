package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class StopWords implements Filter {
    public static List<String> applyStopWords(List<String> input) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/statswidgets/workspace/pipe-and-filter/inputs/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
        List<String> stopWords = input.stream().filter(e -> !stopwords.contains(e)).collect(Collectors.toList());
        System.out.println("stopWords = " + stopWords);
        return stopWords;
    }

    @Override
    public List<String> doWork(List<String> input) throws IOException {
        return StopWords.applyStopWords(input);
    }
}
