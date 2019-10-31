package javaben.mapping.solver;

import javaben.mapping.Vertex;

public class LineSolver extends Solver {

	@Override
	public String solve() {
		for (int i = 0; i < network.getVerticesCount(); i++) {
			super.build(0, i, Vertex.builder().id(i).build());
		}

		return super.export();
	}

	@Override
	public void clean() {
		super.clean();
	}
}
