package movies;

import java.io.IOException;
import java.util.List;

public abstract class Filter {
    private StopWatch stopWatch;

    public abstract List<String> doWork(List<String> input) throws IOException;

    public void startTimer() {
        stopWatch = new StopWatch();
    }

    public void stopTimer() {
        stopWatch.stop();
        System.out.println(this.getClass() + " Milliseconds " + stopWatch.getElapsedMilliseconds());
    }
}
