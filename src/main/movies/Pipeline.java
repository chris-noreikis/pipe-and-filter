package movies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private DataSource dataSource;
    private DataSink dataSink;
    private List<Filter> filters;
    private List<String> state;

    public Pipeline(DataSource dataSource) throws IOException {
        this.dataSource = dataSource;
        this.filters = new ArrayList<>();
        this.state = dataSource.fetchData();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public DataSink getDataSink() {
        return dataSink;
    }

    public void addFilter(Filter f) {
        this.filters.add(f);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSink(DataSink dataSink) {
        this.dataSink = dataSink;
    }

    private void applyFilters() {
        this.filters.forEach(f -> {
            try {
                this.state = f.doWork(this.state);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void printState() {
        this.applyFilters();
        System.out.println("this.state = " + this.state);
        this.dataSink.printTable(this.state);
    }
}
