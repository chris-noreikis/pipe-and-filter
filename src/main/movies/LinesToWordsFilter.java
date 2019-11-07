package movies;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinesToWordsFilter extends Filter {
    @Override
    public List<String> doWork(List<String> input) throws IOException {
        StopWatch.time(LinesToWordsFilter.class.toString());
        List<String> output = input.stream().flatMap(e -> {
            return Arrays.stream(e.replaceAll("[^A-Za-z\\s+]", "").toLowerCase().split("\\s+"));
        }).filter(e -> e.length() > 0).collect(Collectors.toList());
        StopWatch.timeEnd(LinesToWordsFilter.class.toString());
        return output;
    }
}
