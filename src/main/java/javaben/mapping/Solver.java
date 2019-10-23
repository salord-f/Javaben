package javaben.mapping;

import java.util.HashMap;
import java.util.Map;

public abstract class Solver {

    StringBuilder result = new StringBuilder();
	Map<Position, Integer> positions = new HashMap<>();

	abstract String solve(Network network);

	void build(int x, int y) {
		this.result.append(x).append(" ").append(y).append("\n");
	}
}
