package javaben.mapping;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class VertexListNetwork extends EdgeSetNetwork {
    private List<Vertex> vertices;

    public VertexListNetwork() {
        super();
        vertices = new ArrayList<>();
    }

    @Override
    public void parseNetwork(String network) {
        super.parseNetwork(network);
        for (int i = 0; i < getVerticesCount(); i++) {
            vertices.add(new Vertex(i, new ArrayList<>()));
        }
        for (Edge edge : getEdges()) {
            Arrays.stream(edge.getVertices()).forEach(v -> vertices.get(v).getEdges().add(edge));
        }
    }
}
