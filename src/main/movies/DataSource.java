package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataSource {
    public static List<String> getFileContents(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        List<String> foo = Files.readAllLines(path).stream().flatMap(e -> {
            return Arrays.stream(e.replaceAll("[^A-Za-z\\s+]", "").toLowerCase().split("\\s+"));
        }).filter(e -> e.length() > 0).collect(Collectors.toList());
        System.out.println(foo);
        return foo;
    }
}
