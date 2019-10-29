package javaben.mapping.solver;

public class SimpleWideGreedySolver extends AbstractSimpleGreedySolver {
	@Override
	public String solve() {
		maxSize = network.getVerticesCount();
		return super.solve();
	}
}
