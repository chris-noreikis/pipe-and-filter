package movies;

import java.io.IOException;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        Pipeline p = new Pipeline(new DataSource(filePath), new DataSink());
        p.addFilter(new LinesToWords());
        p.addFilter(new StopWords());
        p.addFilter(new StemFilter());
        p.printState();
    }
}
