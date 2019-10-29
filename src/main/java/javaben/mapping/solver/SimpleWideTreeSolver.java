package javaben.mapping.solver;

public class SimpleWideTreeSolver extends AbstractSimpleTreeSolver {
	@Override
	public String solve() {
		maxSize = network.getVerticesCount();
		return super.solve();
	}
}
