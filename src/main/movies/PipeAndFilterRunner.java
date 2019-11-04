package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PipeAndFilterRunner {
    public int runPipeAndFilter()  {
        return 1;
    }

    public List<String> getFileContents(String fileName) throws IOException {
        Path path = FileSystems.getDefault().getPath("/Users/chris/workspace/textprocessing/inputs/foobar.txt");
        return Files.readAllLines(path);
    }
}
