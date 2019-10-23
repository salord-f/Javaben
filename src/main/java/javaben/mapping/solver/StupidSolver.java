package javaben.mapping.solver;

import javaben.mapping.network.Network;

public class StupidSolver extends Solver {

    @Override
    public String solve(Network network) {
        for (int i = 0; i < network.getVerticesCount(); i++) {
            super.build(0, i, i, true);
        }

        return super.export();
    }
}
