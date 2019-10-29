package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;

public class StupidSolver extends Solver {

    @Override
    public String solve(Network network) {
        for (int i = 0; i < network.getVerticesCount(); i++) {
            super.build(0, i, Vertex.builder().id(i).build());
            positionCounters.put(Position.builder().x(0).y(i).build(), 1);
        }

        return super.export();
    }

    @Override
    public void clean() {
        super.clean();
    }
}
