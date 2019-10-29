package javaben.mapping.optimizer;

import javaben.mapping.Edge;
import javaben.mapping.Position;
import javaben.mapping.Vertex;
import javaben.mapping.network.EdgeListNetwork;

import java.util.*;
import java.util.stream.Collectors;

public class NaiveSwapOptimizer {
	private List<Vertex> vertices;

	public NaiveSwapOptimizer(Map<Vertex, Position> positions, EdgeListNetwork network) {
		this.vertices = new ArrayList<>();
		for (Map.Entry<Vertex, Position> entry : positions.entrySet()) {
			entry.getKey().setPosition(entry.getValue());
			vertices.add(entry.getKey());
		}
		if (vertices.get(0).getAdjacents() == null) {
			vertices = vertices.stream().sorted(Comparator.comparingInt(Vertex::getId)).collect(Collectors.toList());
			addAdjacency(network);
		}
	}

	private void addAdjacency(EdgeListNetwork network) {
		for (Vertex vertex : vertices) {
			vertex.setAdjacents(new ArrayList<>());
		}
		for (Edge edge : network.getEdges()) {
			vertices.get(edge.getVertices()[0]).getAdjacents().add(vertices.get(edge.getVertices()[1]));
			vertices.get(edge.getVertices()[1]).getAdjacents().add(vertices.get(edge.getVertices()[0]));
		}
	}

	public Map<Vertex, Position> optimize() {
		for (Vertex v1 : vertices) {
			for (Vertex v2 : vertices) {
				swapIfWorthIt(v1, v2);
			}
		}

		Map<Vertex, Position> positions = new HashMap<>();
		for (Vertex v : vertices) {
			positions.put(v, v.getPosition());
		}
		return positions;
	}

	private void swapIfWorthIt(Vertex v1, Vertex v2) {
		int newScore = 0;

		Position pos1 = v1.getPosition();
		for (Vertex v : v2.getAdjacents()) {
			Position adjPos = v.getPosition();
			if (pos1.equals(adjPos)) {
				return;
			}
			newScore += (2 * Math.pow(pos1.distanceFrom(adjPos) - 1, 2));
		}

		Position pos2 = v2.getPosition();
		for (Vertex v : v1.getAdjacents()) {
			Position adjPos = v.getPosition();
			if (pos2.equals(adjPos)) {
				return;
			}
			newScore += (2 * Math.pow(pos2.distanceFrom(adjPos) - 1, 2));
		}

		int formerScore = 0;

		for (Vertex v : v1.getAdjacents()) {
			Position adjPos = v.getPosition();
			if (pos1.equals(adjPos)) {
				return;
			}
			formerScore += (2 * Math.pow(pos1.distanceFrom(adjPos) - 1, 2));
		}

		for (Vertex v : v2.getAdjacents()) {
			Position adjPos = v.getPosition();
			if (pos2.equals(adjPos)) {
				return;
			}
			formerScore += (2 * Math.pow(pos2.distanceFrom(adjPos) - 1, 2));
		}

		if (newScore < formerScore) {
			v1.setPosition(pos2);
			v2.setPosition(pos1);
		}
	}
}
