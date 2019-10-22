package javaben.mapping;

import java.util.HashSet;
import java.util.Set;

public class EdgeSetNetwork extends Network {
    private Set<Edge> edges;

    public EdgeSetNetwork() {
        edges = new HashSet<>();
    }

    @Override
    public void parseNetwork(String network) {
        super.parseNetwork(network);
        String[] edges = network.substring(network.indexOf("\n") + 1).split("\n");
        for (String edge : edges) {
            String[] v = edge.split(" ");
            this.edges.add(new Edge(new Vertex(Integer.parseInt(v[0])), new Vertex(Integer.parseInt(v[1]))));
        }

    }

    public Set<Edge> getEdges() {
        return edges;
    }
}
