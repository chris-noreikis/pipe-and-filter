package movies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private DataSink dataSink;
    private List<Pipe> pipes;
    private List<String> state;

    public Pipeline(DataSource dataSource, DataSink dataSink) throws IOException {
        this.pipes = new ArrayList<>();
        this.state = dataSource.fetchData();
        this.dataSink = dataSink;
    }

    public void addFilter(Filter f) {
        Pipe pipe = new Pipe(f);
        if (this.pipes.size() > 1) {
            Pipe lastPipe = this.pipes.get(this.pipes.size() - 1);
            lastPipe.setNext(pipe);
        }
        this.pipes.add(pipe);
    }

    private void applyPipes() {
        this.pipes.forEach(p -> {
            try {
                this.state = p.applyFilter(this.state);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void printState() {
        this.applyPipes();
        this.dataSink.printTable(this.state);
    }
}
