package pipeandfilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        StopWatch.time(LinesToWordsFilter.class.toString());
        String joinedOutput = String.join(" ", input);
        String[] o = joinedOutput.replaceAll("[^A-Za-z\\s+]", "").toLowerCase().split("\\s+");
        List<String> output = Arrays.stream(o).filter(e -> e.length() > 0).collect(Collectors.toList());
        StopWatch.timeEnd(LinesToWordsFilter.class.toString());
        return output;
    }
}
