package javaben.mapping;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeListNetworkTest {
    @Test
    public void readFileTest() {
        EdgeListNetwork edgeListNetwork = new EdgeListNetwork();
        edgeListNetwork.readFile("src/main/resources/mapping/GG16.in");

        assertEquals(24, edgeListNetwork.getEdges().size());
        assertEquals(16, edgeListNetwork.getVerticesCount());
        assertEquals(24, edgeListNetwork.getEdgesCount());
    }
}
