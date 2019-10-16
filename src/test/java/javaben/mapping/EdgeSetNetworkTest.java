package javaben.mapping;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeSetNetworkTest {
    @Test
    public void readFileTest() {
        EdgeSetNetwork edgeSetNetwork = new EdgeSetNetwork();
        edgeSetNetwork.readFile("src/main/resources/mapping/GG16.in");

        assertEquals(24, edgeSetNetwork.getEdges().size());
        assertEquals(16, edgeSetNetwork.getVerticesCount());
        assertEquals(24, edgeSetNetwork.getEdgesCount());
    }
}
