package pipeandfilter;

import java.io.IOException;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        PipeFilter dataSource = new DataSource(filePath);
        PipeFilter linesToWords = new LinesToTermsFilter(dataSource);
        PipeFilter stopWordsPipeFilter = new StopWordsFilter(linesToWords);
        PipeFilter StemmerFilter = new StemmerFilter(stopWordsPipeFilter);
        PipeFilter outputPipeFilter = new DataSink(StemmerFilter);

        StopWatch.time("Total Pipeline");
        outputPipeFilter.run();
        StopWatch.timeEnd("Total Pipeline");
        System.out.println("About to print timer");
        StopWatch.printTimerTable();
    }

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        PipeAndFilterRunner runner = new PipeAndFilterRunner();
        runner.runEverything(filename);
    }
}
