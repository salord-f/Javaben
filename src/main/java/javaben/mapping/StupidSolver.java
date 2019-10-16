package javaben.mapping;

public class StupidSolver implements Solver {

	@Override
	public String solve(Network network) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < network.getVerticesCount(); i++) {
			result.append("0 ").append(i).append("\n");
		}

		return result.toString();
	}
}
