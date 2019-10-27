package javaben.mapping.network;

import org.junit.Test;

import java.util.List;

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

    @Test
    public void weightedVertexTest() {
        String input = "5 5\n0 4\n1 0\n2 0\n0 2\n2 4\n";
        EdgeListNetwork network = new EdgeListNetwork();
        network.parseNetwork(input);
        List<Integer> array = network.getWeightedVertex();
        assertEquals(0, (int) array.get(0));
        assertEquals(2, (int) array.get(1));
        assertEquals(4, (int) array.get(2));
        assertEquals(1, (int) array.get(3));
    }
}
