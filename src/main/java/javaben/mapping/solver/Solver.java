package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.*;

@Getter
@Setter
public abstract class Solver {

	protected BidiMap<Position, Vertex> positions = new DualHashBidiMap<>();

	public abstract String solve(Network network);


	protected void build(int x, int y, Vertex value) {
		Position position = Position.builder().x(x).y(y).build();
		if (positions.get(position) == null) {
			positions.put(position, value);
		} else {
			throw new UnsupportedOperationException("Trying to add on same position : " + x + " " + y + " " + value);
		}

	}

	protected String export() {
		StringBuilder builder = new StringBuilder();

		List<Position> ordered = new ArrayList<>();
		positions.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.comparingInt(Vertex::getId)))
				.forEach(v -> ordered.add(v.getKey()));
		for (Position position : ordered) {
			builder.append(position.getX()).append(" ").append(position.getY()).append("\n");
		}

		return builder.toString();

	}

	protected List<Vertex> getAdjacents(Position position) {
		List<Vertex> adjacents = new ArrayList<>();
		return adjacents;
	}

	public Position getClosestFree(int x, int y) {

		/*int dx = 0, dy = -1;
		int offsetx = x;
		int offsety = y;

		while (true) {
			if (-offsetx / 2 < x && x <= offsetx / 2 && -offsety < y && y <= offsety) {
				if (positions.get(Position.builder().x(x).y(y).build()) == null) {
					return Position.builder().x(x).y(y).build();
				}
			}
			if (x == y || (x < 0 && x == -y) || (x > 0 && x == 1 - y)) {
				int tmp = dx;
				dx = -dy;
				dy = tmp;
			}
			x += dx;
			y += dy;
		}*/
		int size = 1;

		while(true) {
			for(int i = 0; i < size; i ++) {
				if (positions.get(Position.builder().x(x + i).y(y + size).build()) == null) {
					return Position.builder().x(x + i).y(y + size).build();
				}
				if (positions.get(Position.builder().x(x - i).y(y + size).build()) == null) {
					return Position.builder().x(x - i).y(y + size).build();
				}
				if (positions.get(Position.builder().x(x + size).y(y + i).build()) == null) {
					return Position.builder().x(x + size).y(y + i).build();
				}
				if (positions.get(Position.builder().x(x + size).y(y - i).build()) == null) {
					return Position.builder().x(x + size).y(y - i).build();
				}

			}
			size++;

		}
	}
}
