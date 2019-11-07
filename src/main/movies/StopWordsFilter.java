package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class StopWordsFilter implements PipeFilter {
    private PipeFilter outputPipeFilter;

    public StopWordsFilter(PipeFilter input) {
        this.outputPipeFilter = input;
    }

    public List<String> applyStopWords(List<String> input) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/statswidgets/workspace/pipe-and-filter/inputs/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
        return input.stream().filter(e -> !stopwords.contains(e)).collect(Collectors.toList());
    }

    @Override
    public List<String> run() throws IOException {
        List<String> input = this.outputPipeFilter.run();
        StopWatch.time(StopWordsFilter.class.toString());
        List<String> output = this.applyStopWords(input);
        StopWatch.timeEnd(StopWordsFilter.class.toString());
        return output;
    }
}
