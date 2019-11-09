package pipeandfilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinesToTermsFilter implements PipeFilter {
    private PipeFilter previousFilter;

    public LinesToTermsFilter(PipeFilter previousFilter) {
        this.previousFilter = previousFilter;
    }

    @Override
    public List<String> run() throws IOException {
        List<String> input = previousFilter.run();
        StopWatch.time(LinesToTermsFilter.class.toString());
        String joinedOutput = String.join(" ", input);
        String[] o = joinedOutput.replaceAll("[^A-Za-z\\s+]", "").toLowerCase().split("\\s+");
        List<String> output = Arrays.stream(o).filter(e -> e.length() > 0).collect(Collectors.toList());
        StopWatch.timeEnd(LinesToTermsFilter.class.toString());
        return output;
    }
}
