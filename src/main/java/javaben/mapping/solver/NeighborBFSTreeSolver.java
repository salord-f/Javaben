package javaben.mapping.solver;

import javaben.mapping.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NeighborBFSTreeSolver extends AbstractNeighborTreeSolver {

	@Override
	public List<Vertex> verticesRoute(List<Vertex> original) {
		Set<Vertex> toExplore = new HashSet<>();
		Set<Vertex> explored = new HashSet<>();
		List<Vertex> ordered = new ArrayList<>();

		while (!original.isEmpty()) {
			toExplore.add(original.get(0));
			original.remove(0);
			breadthFirstSearch(toExplore, explored, ordered, original);
		}

		return ordered;
	}

	private void breadthFirstSearch(Set<Vertex> toExplore, Set<Vertex> explored, List<Vertex> ordered, List<Vertex> original) {
		Set<Vertex> exploreNext = new HashSet<>();
		for (Vertex vertex : toExplore) {
			ordered.add(vertex);
			explored.add(vertex);
			original.remove(vertex);
		}
		for (Vertex vertex : toExplore) {
			exploreNext.addAll(getUnexploredAdj(vertex, explored));
		}

		if (!exploreNext.isEmpty()) {
			breadthFirstSearch(exploreNext, explored, ordered, original);
		}
	}
}
