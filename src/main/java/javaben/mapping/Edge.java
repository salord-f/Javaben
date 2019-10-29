package javaben.mapping;

public class Edge {
	private int[] vertices = new int[2];

	public Edge(int v1, int v2) {
		vertices[0] = v1;
		vertices[1] = v2;
	}

	public int[] getVertices() {
		return vertices;
	}
}
