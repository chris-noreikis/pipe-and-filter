package movies;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PipeAndFilterRunner {
    public void runEverything(String filePath) throws IOException {
        List<String> fileContents = DataSource.getFileContents(filePath);
        List<String> filteredStopWords = StopWords.applyStopWords(fileContents);
        List<String> withDupsRemoved = Stemming.transformIntoRoot(filteredStopWords);
        List<WordCount> withCount = CountWords.getMostFrequentlyOccurringWordCount(withDupsRemoved);
        List<WordCount> sorted = OrderWords.sortAndPrune(withCount);
        DataSink.printTable(sorted);
    }
}
