package javaben.mapping.network;

import javaben.mapping.Edge;
import javaben.mapping.Vertex;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class VertexListNetwork extends EdgeListNetwork {
    private List<Vertex> vertices;

    public VertexListNetwork() {
        super();
        vertices = new ArrayList<>();
    }

    @Override
    public void parseNetwork(String network) {
        super.parseNetwork(network);
        for (int i = 0; i < getVerticesCount(); i++) {
            vertices.add(Vertex.builder()
                    .id(i)
                    .edges(new ArrayList<>())
                    .adjacents(new ArrayList<>())
                    .build());
        }
        for (Edge edge : getEdges()) {
            Arrays.stream(edge.getVertices()).forEach(v -> vertices.get(v).getEdges().add(edge));
            vertices.get(edge.getVertices()[0]).getAdjacents().add(vertices.get(edge.getVertices()[1]));
            vertices.get(edge.getVertices()[1]).getAdjacents().add(vertices.get(edge.getVertices()[0]));
        }
    }
}
