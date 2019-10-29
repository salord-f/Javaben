package javaben.mapping.solver;

import javaben.mapping.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NeighborDFSTreeSolver extends AbstractNeighborTreeSolver {

    @Override
    public List<Vertex> verticesRoute(List<Vertex> original) {
        Set<Vertex> explored = new HashSet<>();
        List<Vertex> ordered = new ArrayList<>();

        while (!original.isEmpty()) {
            Vertex toExplore = original.get(0);
            original.remove(0);
            depthFirstSearch(toExplore, explored, ordered, original);
        }

        return ordered;
    }

    private void depthFirstSearch(Vertex toExplore, Set<Vertex> explored, List<Vertex> ordered, List<Vertex> original) {
        ordered.add(toExplore);
        explored.add(toExplore);
        original.remove(toExplore);

        while (!getUnexploredAdj(toExplore, explored).isEmpty()) {
            depthFirstSearch(getUnexploredAdj(toExplore, explored).get(0), explored, ordered, original);
        }
    }
}
