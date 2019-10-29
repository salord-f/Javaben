package javaben.mapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FaceVertex extends Vertex {

	public FaceVertex(Vertex vertex) {
		super(vertex.getId(), vertex.getEdges(), vertex.getAdjacents(), null);
	}

}
