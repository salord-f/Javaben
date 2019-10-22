package javaben.mapping;

public class SnailSolver extends Solver {

	@Override
	public String solve(Network network) {
		int x = 0, y = 0, dx = 0, dy = -1;
		int snailSize = (int) Math.ceil(Math.sqrt(network.getVerticesCount()));

		for (int i = 0; i < network.getVerticesCount(); ) {
			if (-snailSize / 2 < x && x <= snailSize / 2 && -snailSize < y && y <= snailSize) {
				super.build(x, y);
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

		return result.toString();
	}

}
