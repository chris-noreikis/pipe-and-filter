package pipeandfilter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class StopWordsFilter implements PipeFilter {
    private PipeFilter previousFilter;
    List<String> stopWords;

    public StopWordsFilter(PipeFilter input) throws IOException {
        this.previousFilter = input;
        Path path = FileSystems.getDefault().getPath("src/dist/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
    }

    public List<String> applyStopWords(List<String> input) throws IOException {
        return input.stream().filter(e -> !this.stopWords.contains(e)).collect(Collectors.toList());
    }

    @Override
    public List<String> run() throws IOException {
        List<String> input = this.previousFilter.run();
        StopWatch.time(StopWordsFilter.class.toString());
        List<String> output = this.applyStopWords(input);
        StopWatch.timeEnd(StopWordsFilter.class.toString());
        return output;
    }
}
