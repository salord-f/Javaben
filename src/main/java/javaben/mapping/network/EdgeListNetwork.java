package javaben.mapping.network;

import javaben.mapping.Edge;

import java.util.*;

public class EdgeListNetwork extends Network {
	protected List<Edge> edges;

	public EdgeListNetwork() {
		edges = new ArrayList<>();
	}

	@Override
	public void parseNetwork(String network) {
		super.parseNetwork(network);
		String[] edges = network.substring(network.indexOf("\n") + 1).split("\n");
		for (String edge : edges) {
			String[] v = edge.split(" ");
			this.edges.add(new Edge(Integer.parseInt(v[0]), Integer.parseInt(v[1])));
		}

	}

	public List<Edge> getEdges() {
		return edges;
	}

	public List<Integer> getWeightedVertex() {
		Map<Integer, Integer> vertexCount = new LinkedHashMap<>();
		Set<Integer> verticesLeft = new HashSet<>();

		for (int i = 0; i < verticesCount; i++) {
			verticesLeft.add(i);
		}

		for (Edge edge : edges) {
			vertexCount.merge(edge.getVertices()[0], 1, Integer::sum);
			vertexCount.merge(edge.getVertices()[1], 1, Integer::sum);
		}

		List<Integer> ordered = new ArrayList<>();
		vertexCount.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.forEach(v -> ordered.add(v.getKey()));

		verticesLeft.removeAll(ordered);
		ordered.addAll(verticesLeft);

		return ordered;
	}

	@Override
	public void clean() {
		super.clean();
		edges.clear();
	}
}
