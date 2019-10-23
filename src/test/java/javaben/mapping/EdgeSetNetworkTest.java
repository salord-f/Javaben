package javaben.mapping;

import org.junit.Test;

import java.util.List;
import java.util.Set;

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

    @Test
    public void weightedVertexTest() {
        String input = "5 5\n0 4\n1 0\n2 0\n0 2\n2 4\n";
        EdgeSetNetwork network = new EdgeSetNetwork();
        network.parseNetwork(input);
        List<Integer> array = network.getWeightedVertex();
        assertEquals(0, (int) array.get(0));
        assertEquals(2, (int) array.get(1));
        assertEquals(4, (int) array.get(2));
        assertEquals(1, (int) array.get(3));
    }
}
