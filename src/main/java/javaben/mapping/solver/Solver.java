package javaben.mapping.solver;

import javaben.mapping.Position;
import javaben.mapping.Size;
import javaben.mapping.Vertex;
import javaben.mapping.network.Network;
import javaben.structure.Tuple;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Stream;

@Getter
@Setter
public abstract class Solver {

	protected Map<Vertex, Position> positions = new HashMap<>();
	protected Map<Position, Integer> positionCounters = new HashMap<>();

	public <K, V> Stream<K> keys(Map<K, V> map, V value) {
		return map
				.entrySet()
				.stream()
				.filter(entry -> value.equals(entry.getValue()))
				.map(Map.Entry::getKey);
	}

	public abstract String solve(Network network);

	protected void build(int x, int y, Vertex value) {
		Position position = Position.builder().x(x).y(y).build();
		positions.put(value, position);
	}

	protected String export() {
		StringBuilder builder = new StringBuilder();

		List<Position> ordered = new ArrayList<>();
		positions.entrySet().stream()
				.sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Vertex::getId)))
				.forEach(v -> ordered.add(v.getValue()));
		for (Position position : ordered) {
			builder.append(position.getX()).append(" ").append(position.getY()).append("\n");
		}

		return builder.toString();

	}

	public Tuple<Integer, Size> additionalScoreAndNewSize(Position newPosition, Vertex v, Size formerSize) {
		int score = 0;

        for (Vertex vertex : v.getAdjacents()) {
            Position position = vertex.getPosition();
            if (position != null) {
                if (position.equals(newPosition)) {
                    return new Tuple<>(1000000000, null);
                }
                score += (2 * Math.pow(position.distanceFrom(newPosition) - 1, 2));
            }
        }

        Integer overlaps = positionCounters.get(newPosition);

        if (overlaps != null) {
            score += ((2 * Math.pow(overlaps, 2)) - (2 * Math.pow(overlaps - 1, 2)));
		}

		Size newSize;

		if (formerSize == null) {
			newSize = new Size(newPosition.getX(), newPosition.getY(), newPosition.getX(), newPosition.getY());
		} else {
			newSize = new Size(Math.min(newPosition.getX(), formerSize.getMinX()), Math.min(newPosition.getY(), formerSize.getMinY()), Math.max(newPosition.getX(), formerSize.getMaxX()), Math.max(newPosition.getY(), formerSize.getMaxY()));
			score += (newSize.getSizeScore() - formerSize.getSizeScore());
		}

		return new Tuple<>(score, newSize);
	}

	public Position getClosestFree(int x, int y) {
		Set<Position> toExplore = new HashSet<>();
		Set<Position> explored = new HashSet<>();

		toExplore.add(Position.builder().x(x).y(y).build());

		return closestSearch(toExplore, explored);
	}

	private Position closestSearch(Set<Position> toExplore, Set<Position> explored) {
		Set<Position> exploreNext = new HashSet<>();
		for (Position pos : toExplore) {
			if (positionCounters.get(pos) == null) {
				return pos;
			} else {
				explored.add(pos);
				exploreNext.addAll(getUnexploredNeighbors(pos, explored));
			}
		}

		return closestSearch(exploreNext, explored);
	}

	private List<Position> getUnexploredNeighbors(Position position, Set<Position> explored) {
		List<Position> res = new ArrayList<>();
		Position position1 = Position.builder().x(position.getX() - 1).y(position.getY()).build();
		if (!explored.contains(position1) && position.getX() > 0) {
			res.add(position1);
		}
		Position position2 = Position.builder().x(position.getX() + 1).y(position.getY()).build();
		if (!explored.contains(position2)) {
			res.add(position2);
		}
		Position position3 = Position.builder().x(position.getX()).y(position.getY() - 1).build();
		if (!explored.contains(position3) && position.getY() > 0) {
			res.add(position3);
		}
		Position position4 = Position.builder().x(position.getX()).y(position.getY() + 1).build();
		if (!explored.contains(position4)) {
			res.add(position4);
		}
		return res;
	}

	public void clean() {
		positions.clear();
		positionCounters.clear();
	}
}
