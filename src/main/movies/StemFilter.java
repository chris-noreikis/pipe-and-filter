package movies;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StemFilter implements Filter {
    public static List<String> transformIntoRoot(List<String> input) throws IOException {
        return input
                .stream()
                .map(e -> {
                    Stemmer s = new Stemmer();
                    s.add(e.toCharArray(), e.length());
                    s.stem();
                    return s.toString();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> doWork(List<String> input) throws IOException {
        StopWatch.time(StemFilter.class.toString());
        List<String> output = StemFilter.transformIntoRoot(input);
        StopWatch.timeEnd(StemFilter.class.toString());
        return output;
    }
}
