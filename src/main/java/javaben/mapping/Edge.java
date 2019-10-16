package javaben.mapping;

public class Edge {
    private Vertex[] vertices = new Vertex[2];

    public Edge(Vertex v1, Vertex v2) {
        vertices[0] = v1;
        vertices[1] = v2;
    }

    public Vertex[] getVertices() {
        return vertices;
    }
}
