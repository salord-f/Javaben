package javaben.mapping.solver;

public class SimpleTightGreedySolver extends AbstractSimpleGreedySolver {
	@Override
	public String solve() {
		maxSize = (int) Math.ceil(Math.sqrt(network.getVerticesCount()));
		return super.solve();
	}
}
