package movies;

import java.io.IOException;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        Pipeline p = new Pipeline(new DataSource(filePath));
        p.setDataSink(new DataSink());
        p.addFilter(new StopWords());
        p.addFilter(new Stemming());
        p.printState();
    }
}
