package javaben.mapping;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {

	private int x;
	private int y;

	public int distanceFrom(Position position) {
		return Math.abs(x - position.x) + Math.abs(y - position.y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Position position = (Position) o;

		if (x != position.x) return false;
		return y == position.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
