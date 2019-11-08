package pipeandfilter;

import java.io.IOException;
import java.util.List;

public class MockFilter implements PipeFilter {
    List<String> testInput;

    public MockFilter(List<String> testInput) {
        this.testInput = testInput;
    }

    @Override
    public List<String> run() throws IOException {
        return testInput;
    }
}
