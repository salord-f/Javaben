package javaben.structure;

import java.util.Objects;

public class Tuple<Left, Right> {
	public final Left left;
	public final Right right;

	public Tuple(Left left, Right right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tuple<?, ?> tuple = (Tuple<?, ?>) o;

		if (!Objects.equals(left, tuple.left)) return false;
		return Objects.equals(right, tuple.right);
	}

	@Override
	public int hashCode() {
		int result = left != null ? left.hashCode() : 0;
		result = 31 * result + (right != null ? right.hashCode() : 0);
		return result;
	}
}