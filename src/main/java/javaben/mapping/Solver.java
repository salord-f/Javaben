package javaben.mapping;

public abstract class Solver {

    StringBuilder result = new StringBuilder();

	abstract String solve(Network network);

	void build(int x, int y) {
		this.result.append(x).append(" ").append(y).append("\n");
	}
}
