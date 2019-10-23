package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.network.Network;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class Solver {

	protected BidiMap<Position, Integer> positions = new DualHashBidiMap<>();

	public abstract String solve(Network network);


	protected void build(int x, int y, int value, boolean duplicate) {
		if (!duplicate) {
			if (positions.getKey(value) == null) {
				positions.put(Position.builder().x(x).y(y).build(), value);
			}
		} else {
			positions.put(Position.builder().x(x).y(y).build(), value);
		}

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
