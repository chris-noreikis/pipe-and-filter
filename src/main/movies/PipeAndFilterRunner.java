package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PipeAndFilterRunner {
    public int runPipeAndFilter() {
        return 1;
    }

    public List<String> getFileContents(String fileName) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/chris/workspace/textprocessing/inputs/foobar.txt");
        return Files.readAllLines(path);
    }

    public List<String> applyStopWords(List<String> input) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/chris/workspace/textprocessing/inputs/stopwords.txt");
        List<String> stopwords = Files.readAllLines(path);
        return input.stream().filter(e -> !stopwords.contains(e)).collect(Collectors.toList());
    }

    public List<String> removeDupList(List<String> list, boolean ignoreCase) {
        Set<String> set = (ignoreCase ? new TreeSet<String>(String.CASE_INSENSITIVE_ORDER) : new LinkedHashSet<String>());
        set.addAll(list);

        List<String> res = new ArrayList<String>(set);
        return res;
    }

    public List<String> transformIntoRoot(List<String> input) throws IOException {
        List<String> prelimInput = input
                .stream()
                .map(e -> {
                    Stemmer s = new Stemmer();
                    s.add(e.toCharArray(), e.length());
                    s.stem();
                    return s.toString();
                })
                .collect(Collectors.toList());
        System.out.println(prelimInput);
        return removeDupList(prelimInput, true);
    }
}
