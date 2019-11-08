package pipeandfilter;

import java.util.HashMap;

public class StopWatch {
    static HashMap<String, Long> labels = new HashMap<>();

    public static void time(String label) {
        labels.put(label, System.currentTimeMillis());
    }

    public static void timeEnd(String label) {
        Long startTime = labels.get(label);
        if (labels.containsKey(label)) {
            Long endTime = System.currentTimeMillis();
            Long ellapsedMilliseconds =  endTime - startTime;
            System.out.println(String.format("%45s | Milliseconds | %4s", label, ellapsedMilliseconds));
        }
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
