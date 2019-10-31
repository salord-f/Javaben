package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.Size;
import javaben.mapping.Vertex;
import javaben.mapping.network.VertexListNetwork;
import javaben.structure.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractGreedyNeighborSolver extends Solver {
	private final int N_NEAREST = 3;
	private List<Vertex> vertices;

	@Override
	public String solve() {
		VertexListNetwork vertexListNetwork = (VertexListNetwork) network;
		vertices = vertexListNetwork.getWeightedVertex().stream().map(v -> vertexListNetwork.getVertices().get(v)).collect(Collectors.toList());

		vertices = verticesRoute(vertices);

		int offset = network.getVerticesCount();
		Position position = Position.builder().x(offset).y(offset).build();
		vertices.get(0).setPosition(position);
		positionCounters.put(position, 1);
		positions.put(vertices.get(0), position);

		solve_i(1, new Size(position.getX(), position.getY(), position.getX(), position.getY()));
		return export();
	}

	public abstract List<Vertex> verticesRoute(List<Vertex> original);

	protected List<Vertex> getUnexploredAdj(Vertex vertex, Set<Vertex> explored) {
		List<Vertex> res = new ArrayList<>();
		for (Vertex adj : vertex.getAdjacents()) {
			if (!explored.contains(adj)) {
				res.add(adj);
			}
		}
		return res;
	}

	private void solve_i(int beginIndex, Size beginSize) {
		Size size = beginSize;
		for (int i = beginIndex; i < vertices.size(); i++) {
			Vertex curr = vertices.get(i);

			Tuple<Tuple<Position, Integer>, Size> bestPosition = null;
			List<Position> neighbors = curr.getAdjacents().stream()
					.map(Vertex::getPosition)
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			for (Position neighborLocation : neighbors) {
				for (int x = neighborLocation.getX() - N_NEAREST; x <= neighborLocation.getX() + N_NEAREST; x++) {
					for (int y = neighborLocation.getY() - N_NEAREST; y <= neighborLocation.getY() + N_NEAREST; y++) {
						Position position = Position.builder().x(x).y(y).build();
						try {
							Tuple<Integer, Size> scoreAndSize = additionalScoreAndNewSize(position, curr, size);
							if (bestPosition == null || bestPosition.left.right > scoreAndSize.left) {
								bestPosition = new Tuple<>(new Tuple<>(position, scoreAndSize.left), scoreAndSize.right);
							}
						} catch (IllegalStateException ignored) {
						}
					}
				}
				Position closestFree = getClosestFree(neighborLocation.getX(), neighborLocation.getY());
				Tuple<Integer, Size> scoreAndSize = additionalScoreAndNewSize(closestFree, curr, size);
				if (bestPosition == null || bestPosition.left.right > scoreAndSize.left) {
					bestPosition = new Tuple<>(new Tuple<>(closestFree, scoreAndSize.left), scoreAndSize.right);
				}
			}

			if (bestPosition == null) {
				int width = size.getMaxX() - size.getMinX();
				int height = size.getMaxY() - size.getMinY();
				if (width > height) {
					int x = (int) Math.ceil((size.getMaxX() + size.getMinX()) / 2.0);
					Size newSize = new Size(size.getMinX(), size.getMinY(), size.getMaxX(), size.getMaxY() + 1);
					bestPosition = new Tuple<>(new Tuple<>(Position.builder().x(x).y(size.getMaxY() + 1).build(), 0), newSize);
				} else {
					int y = (int) Math.ceil((size.getMaxY() + size.getMinY()) / 2.0);
					Size newSize = new Size(size.getMinX(), size.getMinY(), size.getMaxX() + 1, size.getMaxY());
					bestPosition = new Tuple<>(new Tuple<>(Position.builder().x(size.getMaxX() + 1).y(y).build(), 0), newSize);
				}
			}

			Position position = bestPosition.left.left;
			positions.put(curr, position);
			curr.setPosition(position);
			try {
				int formerCounter = positionCounters.get(position);
				positionCounters.put(position, formerCounter + 1);
			} catch (NullPointerException e) {
				positionCounters.put(position, 1);
			}
			size = bestPosition.right;
		}


	}
}
