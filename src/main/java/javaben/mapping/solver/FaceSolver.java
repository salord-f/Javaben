package javaben.mapping.solver;

import javaben.mapping.FaceVertex;
import javaben.mapping.Position;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class FaceSolver extends Solver {

	private List<FaceVertex> vertices = new ArrayList<>();

	@Override
	public String solve(Network network) {
		for (Vertex vertex : ((VertexListNetwork) network).getVertices()) {
			vertices.add(new FaceVertex(vertex));
		}
		int offset = network.offset();

		positions.put(Position.builder().x(offset).y(offset).build(), vertices.get(0).getId());
		while(positions.size() != vertices.size()) {
			for (FaceVertex faceVertex : vertices) {
				try {
					Position pos = positions.getKey(faceVertex.getId());
					super.build(
							pos.getX() + 1,
							pos.getY(),
							faceVertex.getAdjacents().get(0).getId(),
							false);
					super.build(pos.getX(), pos.getY() + 1, faceVertex.getAdjacents().get(1).getId(), false);
					super.build(pos.getX() - 1, pos.getY(), faceVertex.getAdjacents().get(2).getId(), false);
					super.build(pos.getX(), pos.getY() - 1, faceVertex.getAdjacents().get(3).getId(), false);
					for (int i = 4; i < faceVertex.getAdjacents().size(); i++) {
						Vertex vertex = faceVertex.getAdjacents().get(i);
						super.build(pos.getX(), pos.getY(), vertex.getId(), false);
					}
				} catch (IndexOutOfBoundsException | NullPointerException e) {
					// ignore
				}
		}

		}


		return export();
	}
}
