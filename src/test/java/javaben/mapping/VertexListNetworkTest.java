package javaben.mapping;

import javaben.mapping.network.VertexListNetwork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VertexListNetworkTest {
    @Test
    public void readFileTest() {
        VertexListNetwork vertexListNetwork = new VertexListNetwork();
        vertexListNetwork.readFile("src/main/resources/mapping/GG16.in");

        assertEquals(24, vertexListNetwork.getEdges().size());
        assertEquals(16, vertexListNetwork.getVerticesCount());
        assertEquals(24, vertexListNetwork.getEdgesCount());
        assertEquals(16, vertexListNetwork.getVertices().size());
    }
}
