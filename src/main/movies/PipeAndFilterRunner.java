package movies;

import java.io.IOException;
import java.nio.channels.Pipe;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        Pipeline p = new Pipeline(new DataSource(filePath));
        p.setDataSink(new DataSink());
        p.addFilter(new StopWords());
        p.addFilter(new Stemming());
        p.printState();
    }
}
