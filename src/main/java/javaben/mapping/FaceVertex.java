package javaben.mapping;

import java.util.List;

public class FaceVertex extends Vertex {
	FaceVertex(int id, List<Edge> edges) {
		super(id, edges);
	}

	private Vertex n;
	private Vertex e;
	private Vertex w;
	private Vertex s;

}
