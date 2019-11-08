package pipeandfilter;

import java.io.IOException;
import java.util.List;

public interface PipeFilter {
    List<String> run() throws IOException;
}
