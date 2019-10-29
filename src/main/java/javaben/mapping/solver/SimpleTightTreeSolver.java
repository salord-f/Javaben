package javaben.mapping.solver;

import javaben.mapping.network.Network;

public class SimpleTightTreeSolver extends AbstractSimpleTreeSolver {
    @Override
    public String solve(Network network) {
        maxSize = (int) Math.ceil(Math.sqrt(network.getVerticesCount()));
        return super.solve(network);
    }
}
