package movies;

import javafx.scene.paint.Stop;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class StopWordsFilter implements Filter {
    public List<String> applyStopWords(List<String> input) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/statswidgets/workspace/pipe-and-filter/inputs/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
        List<String> stopWords = input.stream().filter(e -> !stopwords.contains(e)).collect(Collectors.toList());
        return stopWords;
    }

    @Override
    public List<String> doWork(List<String> input) throws IOException {
        StopWatch.time(StopWordsFilter.class.toString());
        List<String> output = this.applyStopWords(input);
        StopWatch.timeEnd(StopWordsFilter.class.toString());
        return output;
    }
}
