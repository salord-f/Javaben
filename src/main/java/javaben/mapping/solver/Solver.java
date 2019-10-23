package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.network.Network;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public abstract class Solver {

	private Map<Position, Integer> positions = new HashMap<>();

	abstract String solve(Network network);


	protected void build(int x, int y, int value) {
		positions.put(Position.builder().x(x).y(y).build(), value);
	}

	protected String export() {
		StringBuilder builder = new StringBuilder();

		List<Position> ordered = new ArrayList<>();
		positions.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.forEach(v -> ordered.add(v.getKey()));
		for (Position position : ordered) {
			builder.append(position.getX()).append(" ").append(position.getY()).append("\n");
		}

		return builder.toString();

	}
}
