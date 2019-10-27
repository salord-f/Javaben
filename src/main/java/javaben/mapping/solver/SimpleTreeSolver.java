package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.Size;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;
import javaben.structure.Tuple;
import javaben.structure.immutable.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleTreeSolver extends Solver {
    private final int N_EXPAND = 10;
    private List<Vertex> vertices;
    private List<Tuple<ImmutableList<Tuple<Vertex, Position>>, Integer>> paths = new ArrayList<>();
    private int maxSize;

    @Override
    public String solve(Network network) {
        maxSize = network.getVerticesCount();
        VertexListNetwork vertexListNetwork = (VertexListNetwork) network;
        vertices = vertexListNetwork.getWeightedVertex().stream().map(v -> vertexListNetwork.getVertices().get(v)).collect(Collectors.toList());
        Tuple<ImmutableList<Tuple<Vertex, Position>>, Integer> initPath = new Tuple<>(new ImmutableList<>(), 0);
        solve_r(initPath, vertices.get(0), null);
        for (Tuple<ImmutableList<Tuple<Vertex, Position>>, Integer> path : paths) {
            if (initPath.right == 0 || path.right < initPath.right) {
                initPath = path;
            }
        }
        for (Tuple<Vertex, Position> vertexPositionTuple : initPath.left) {
            getPositions().put(vertexPositionTuple.left, vertexPositionTuple.right);
            System.out.println(String.format("Vertex %s / %s %s", vertexPositionTuple.left.getId(), vertexPositionTuple.right.getX(), vertexPositionTuple.right.getY()));
        }
        System.out.println(export());
        return export();
    }

    private void solve_r(Tuple<ImmutableList<Tuple<Vertex, Position>>, Integer> currPath, Vertex curr, Size size) {
        List<Tuple<Tuple<Position, Integer>, Size>> bestPositions = new ArrayList<>();
        Size newSize = null;
        for (int x = 0; x < maxSize; x++) {
            outerLoop:
            for (int y = 0; y < maxSize; y++) {
                Position position = Position.builder().x(x).y(y).build();
                int score = 0;
                int overlaps = 1;
                for (Tuple<Vertex, Position> vertexPositionTuple : currPath.left) {
                    if (position.equals(vertexPositionTuple.right)) {
                        if (vertexPositionTuple.left.getAdjacents().contains(curr)) {
                            continue outerLoop;
                        }
                        overlaps++;
                    }
                    if (vertexPositionTuple.left.getAdjacents().contains(curr)) {
                        score += (2 * Math.pow(position.distanceFrom(vertexPositionTuple.right) - 1, 2));
                    }
                }
                if (overlaps > 1) {
                    score += ((2 * Math.pow(overlaps - 1, 2)) - (2 * Math.pow(overlaps - 2, 2)));
                }
                if (size == null) {
                    newSize = new Size(position.getX(), position.getY(), position.getX(), position.getY());
                } else {
                    newSize = new Size(Math.min(position.getX(), size.getMinX()), Math.min(position.getY(), size.getMinY()), Math.min(position.getX(), size.getMaxX()), Math.min(position.getY(), size.getMaxY()));
                    score += (newSize.getSizeScore() - size.getSizeScore());
                }

                if (bestPositions.size() < N_EXPAND) {
                    bestPositions.add(new Tuple<>(new Tuple<>(position, score), newSize));
                } else {
                    int maxScore = score;
                    int maxScoreIndex = -1;
                    for (int i = 0; i < bestPositions.size(); i++) {
                        Tuple<Tuple<Position, Integer>, Size> positionScoreTuple = bestPositions.get(i);
                        if (positionScoreTuple.left.right > maxScore) {
                            maxScore = positionScoreTuple.left.right;
                            maxScoreIndex = i;
                        }
                    }
                    if (maxScoreIndex != -1) {
                        bestPositions.set(maxScoreIndex, new Tuple<>(new Tuple<>(position, score), newSize));
                    }
                }
            }
        }
        for (Tuple<Tuple<Position, Integer>, Size> positionScoreTuple : bestPositions) {
            Position position = positionScoreTuple.left.left;
            int newScore = positionScoreTuple.left.right + currPath.right;
            ImmutableList<Tuple<Vertex, Position>> newList = currPath.left.add(new Tuple<>(curr, position));
            Vertex next;
            try {
                next = next(curr);
            } catch (IndexOutOfBoundsException e) {
                paths.add(new Tuple<>(newList, newScore));
                return;
            }
            solve_r(new Tuple<>(newList, newScore), next, positionScoreTuple.right);
        }

    }

    private Vertex next(Vertex vertex) {
        return vertices.get(vertices.indexOf(vertex) + 1);
    }
}
