package movies;

import java.io.IOException;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        Pipeline p = new Pipeline(new DataSource(filePath), new DataSink());
        p.addFilter(new LinesToWordsFilter());
        p.addFilter(new StopWordsFilter());
        p.addFilter(new StemFilter());
        p.printState();
    }
}
