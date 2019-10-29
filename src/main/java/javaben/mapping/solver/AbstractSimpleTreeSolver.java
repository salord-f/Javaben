package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.Size;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;
import javaben.structure.Tuple;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractSimpleTreeSolver extends Solver {

    protected int maxSize;
    private List<Vertex> vertices;

    @Override
    public String solve(Network network) {
        VertexListNetwork vertexListNetwork = (VertexListNetwork) network;
        vertices = vertexListNetwork.getWeightedVertex().stream().map(v -> vertexListNetwork.getVertices().get(v)).collect(Collectors.toList());
        solve_r(vertices.get(0), null);
        return export();
    }

    private void solve_r(Vertex curr, Size size) {
        Tuple<Tuple<Position, Integer>, Size> bestPosition = null;
        for (int x = 0; x < maxSize; x++) {
            for (int y = 0; y < maxSize; y++) {
                Position position = Position.builder().x(x).y(y).build();

                Tuple<Integer, Size> scoreAndSize = additionalScoreAndNewSize(position, curr, size);
                if (bestPosition == null || bestPosition.left.right > scoreAndSize.left) {
                    bestPosition = new Tuple<>(new Tuple<>(position, scoreAndSize.left), scoreAndSize.right);
                }

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

        Vertex next;
        try {
            next = next(curr);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        solve_r(next, bestPosition.right);

    }

    private Vertex next(Vertex vertex) {
        return vertices.get(vertices.indexOf(vertex) + 1);
    }

    @Override
    public void clean() {
        super.clean();
        vertices.clear();
    }
}
