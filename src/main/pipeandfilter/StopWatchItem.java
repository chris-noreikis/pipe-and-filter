package pipeandfilter;

public class StopWatchItem {
    private long startTime;
    private long endTime;

    public StopWatchItem() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long elapsedTime() {
        return this.endTime - this.startTime;
    }
}
