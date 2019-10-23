package javaben.mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaceVertex extends Vertex {

	public FaceVertex(Vertex vertex) {
		super(vertex.getId(), vertex.getEdges(), vertex.getAdjacents());
	}

}
