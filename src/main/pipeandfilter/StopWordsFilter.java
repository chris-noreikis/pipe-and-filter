package pipeandfilter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class StopWordsFilter implements PipeFilter {
    private PipeFilter previousFilter;

    public StopWordsFilter(PipeFilter input) {
        this.previousFilter = input;
    }

    public List<String> applyStopWords(List<String> input) throws IOException {
        Path path = FileSystems.getDefault().getPath("src/dist/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
        return input.stream().filter(e -> !stopwords.contains(e)).collect(Collectors.toList());
    }

    @Override
    public List<String> run() throws IOException {
        List<String> input = this.previousFilter.run();
        List<String> output = this.applyStopWords(input);
        return output;
    }
}
