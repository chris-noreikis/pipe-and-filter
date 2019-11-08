package pipeandfilter;

import java.util.HashMap;

public class StopWatch {
    static HashMap<String, StopWatchItem> labels = new HashMap<>();

    public static void time(String label) {
        StopWatchItem item = new StopWatchItem();
        labels.put(label, item);
    }

    public static void timeEnd(String label) {
        StopWatchItem item = labels.get(label);
        if (labels.containsKey(label)) {
            item.stop();
        }
    }

    public static void printTimerTable() {
        labels.forEach((key, value) -> System.out.println(String.format("%45s | Milliseconds | %4s", key, value.elapsedTime())));
    }

    private long startTime;
    private long endTime;

    public StopWatch() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long getElapsedMilliseconds() {
        return this.endTime - this.startTime;
    }
}
