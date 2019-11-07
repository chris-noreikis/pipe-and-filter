package movies;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StemmerFilter implements Filter {
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
        StopWatch.time(StemmerFilter.class.toString());
        List<String> output = StemmerFilter.transformIntoRoot(input);
        StopWatch.timeEnd(StemmerFilter.class.toString());
        return output;
    }
}
