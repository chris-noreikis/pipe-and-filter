package pipeandfilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinesToWordsFilter implements PipeFilter {
    private PipeFilter previousFilter;

    public LinesToWordsFilter(PipeFilter previousFilter) {
        this.previousFilter = previousFilter;
    }

    @Override
    public List<String> run() throws IOException {
        List<String> input = previousFilter.run();
        List<String> output = input.stream().flatMap(e -> {
            return Arrays.stream(e.replaceAll("[^A-Za-z\\s+]", "").toLowerCase().split("\\s+"));
        }).filter(e -> e.length() > 0).collect(Collectors.toList());
        return output;
    }
}
