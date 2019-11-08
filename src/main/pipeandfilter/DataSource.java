package pipeandfilter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataSource implements PipeFilter {
    private List<String> lines;

    public DataSource(String filepath) throws IOException {
        this.lines = this.getFileContents(filepath);
    }

    public List<String> getFileContents(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(filePath);
        System.out.println("                     Parsing: " + path.getFileName());
        return Files.readAllLines(path);
    }

    @Override
    public List<String> run() throws IOException {
        return lines;
    }
}
