package movies;

import java.io.IOException;
import java.util.List;

public interface Filter {
    public List<String> doWork(List<String> input) throws IOException;
}
