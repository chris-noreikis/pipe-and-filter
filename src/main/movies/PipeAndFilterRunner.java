package movies;

import java.io.IOException;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        PipeFilter dataSource = new DataSource(filePath);
        PipeFilter linesToWords = new LinesToWordsFilter(dataSource);
        PipeFilter stopWordsPipeFilter = new StopWordsFilter(linesToWords);
        PipeFilter PipeFilter = new StemmerFilter(stopWordsPipeFilter);
        PipeFilter outputPipeFilter = new DataSink(PipeFilter);

        outputPipeFilter.run();
    }
}
