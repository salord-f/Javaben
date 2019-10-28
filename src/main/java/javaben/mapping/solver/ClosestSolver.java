package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;

import java.util.ArrayList;
import java.util.List;

public class ClosestSolver extends Solver {

    private List<Vertex> done = new ArrayList<>();

    @Override
    public String solve(Network network) {
        int offset = network.offset();


        for (Vertex vertex : ((VertexListNetwork) network).getVertices()) {
            if (!done.contains(vertex)) {
                System.out.println(vertex.getId());
                Position position = super.getClosestFree(offset, offset);
                done.add(vertex);
                super.build(position.getX(), position.getY(), vertex);
                positionCounters.put(position, 1);
                for (Vertex neighbour : vertex.getAdjacents()) {
                    if (!done.contains(neighbour)) {
                        System.out.println(neighbour.getId());
                        Position neighbourPosition = super.getClosestFree(position.getX(), position.getY());
                        super.build(neighbourPosition.getX(), neighbourPosition.getY(), neighbour);
                        positionCounters.put(neighbourPosition, 1);
                        done.add(neighbour);
                    }
                }
            }
        }
        return export();
    }

    @Override
    public void clean() {
        super.clean();
        this.done.clear();
    }
}
