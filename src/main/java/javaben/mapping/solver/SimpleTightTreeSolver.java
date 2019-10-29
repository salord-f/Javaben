package javaben.mapping.solver;

public class SimpleTightTreeSolver extends AbstractSimpleTreeSolver {
	@Override
	public String solve() {
		maxSize = (int) Math.ceil(Math.sqrt(network.getVerticesCount()));
		return super.solve();
	}
}
