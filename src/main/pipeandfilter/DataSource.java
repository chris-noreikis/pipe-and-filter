package pipeandfilter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataSource implements PipeFilter {
    private String filepath;
    public DataSource(String filepath) {
        this.filepath = filepath;
    }

    public static List<String> getFileContents(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        System.out.println("\n                     Parsing: " + path.getFileName());
        return Files.readAllLines(path);
    }

    @Override
    public List<String> run() throws IOException {
        return DataSource.getFileContents(this.filepath);
    }
}
