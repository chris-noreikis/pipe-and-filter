package movies;

import java.io.IOException;
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
