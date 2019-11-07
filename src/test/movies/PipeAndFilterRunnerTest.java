package movies;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PipeAndFilterRunnerTest {
    private PipeAndFilterRunner g;

    @Before
    public void setUp()
    {
        g = new PipeAndFilterRunner();
    }

    @Test
    public void canRunIntegrationTestWithoutExplodingLoudly() throws IOException {
        g.runEverything("/Users/statswidgets/workspace/pipe-and-filter/inputs/usdeclar.txt");
    }
}
