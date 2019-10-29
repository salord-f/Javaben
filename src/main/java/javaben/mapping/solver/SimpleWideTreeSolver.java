package javaben.mapping.solver;

import javaben.mapping.network.Network;

public class SimpleWideTreeSolver extends AbstractSimpleTreeSolver {
    @Override
    public String solve(Network network) {
        maxSize = network.getVerticesCount();
        return super.solve(network);
    }
}
