package movies;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        PipeFilter dataSource = new DataSource(filePath);
        PipeFilter linesToWords = new LinesToWordsFilter(dataSource);
        PipeFilter stopWordsPipeFilter = new StopWordsFilter(linesToWords);
        PipeFilter StemmerFilter = new StemmerFilter(stopWordsPipeFilter);
        PipeFilter outputPipeFilter = new DataSink(StemmerFilter);

        outputPipeFilter.run();
    }

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        PipeAndFilterRunner runner = new PipeAndFilterRunner();
        runner.runEverything(filename);
    }
}
