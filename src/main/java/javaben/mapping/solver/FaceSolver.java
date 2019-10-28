package javaben.mapping.solver;

import javaben.mapping.FaceVertex;
import javaben.mapping.Position;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;

import java.util.ArrayList;
import java.util.List;

public class FaceSolver extends Solver {

	private List<FaceVertex> vertices = new ArrayList<>();

	@Override
	public String solve(Network network) {
		for (Vertex vertex : ((VertexListNetwork) network).getVertices()) {
			vertices.add(new FaceVertex(vertex));
			System.out.println(vertex.getId());
		}
		int offset = network.offset();
		//vertices.forEach(System.out::println);

		positions.put(vertices.get(0), Position.builder().x(offset).y(offset).build());
		/*while (positions.size() != vertices.size()) {
			for (FaceVertex faceVertex : vertices) {
				try {
					Position pos = positions.getKey(faceVertex.getId());
					super.build(pos.getX() + 1, pos.getY(), faceVertex.getAdjacents().get(0));
					super.build(pos.getX(), pos.getY() + 1, faceVertex.getAdjacents().get(1));
					super.build(pos.getX() - 1, pos.getY(), faceVertex.getAdjacents().get(2));
					super.build(pos.getX(), pos.getY() - 1, faceVertex.getAdjacents().get(3));
					for (int i = 4; i < faceVertex.getAdjacents().size(); i++) {
						Vertex vertex = faceVertex.getAdjacents().get(i);
						super.build(pos.getX(), pos.getY(), vertex);
					}
				} catch (IndexOutOfBoundsException | NullPointerException e) {
					// ignore
				}
			}

		}*/


		return export();
	}

	@Override
	public void clean() {
		super.clean();
		vertices.clear();
	}
}
