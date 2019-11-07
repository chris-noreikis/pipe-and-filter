package movies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Pipe {
    private Filter filter;
    private Pipe next;

    Pipe(Filter filter) {
        this.filter = filter;
    }

    public Pipe getNext() {
        return next;
    }

    public void setNext(Pipe next) {
        this.next = next;
    }

    public List<String> applyFilter(List<String> input) throws IOException {
        return this.filter.doWork(input);
    }
}

public class Pipeline {
    private DataSink dataSink;
    private List<Pipe> pipes;
    private List<String> state;

    public Pipeline(DataSource dataSource) throws IOException {
        this.pipes = new ArrayList<>();
        this.state = dataSource.fetchData();
    }

    public void addFilter(Filter f) {
        Pipe pipe = new Pipe(f);
        if (this.pipes.size() > 1) {
            Pipe lastPipe = this.pipes.get(this.pipes.size() - 1);
            lastPipe.setNext(pipe);
        }
        this.pipes.add(pipe);
    }

    public void setDataSink(DataSink dataSink) {
        this.dataSink = dataSink;
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
