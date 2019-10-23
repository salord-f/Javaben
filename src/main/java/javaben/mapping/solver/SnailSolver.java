package javaben.mapping.solver;

import javaben.mapping.network.Network;

public class SnailSolver extends Solver {

	@Override
	public String solve(Network network) {
		int x = 0, y = 0, dx = 0, dy = -1;
		int offset = network.offset();

		for (int i = 0; i < network.getVerticesCount(); ) {
			if (-offset / 2 < x && x <= offset / 2 && -offset < y && y <= offset) {
				super.build(x + offset, y + offset, i);
				i++;
			}
			if (x == y || (x < 0 && x == -y) || (x > 0 && x == 1 - y)) {
				int tmp = dx;
				dx = -dy;
				dy = tmp;
			}
			x += dx;
			y += dy;
		}

		return super.export();
	}

}
