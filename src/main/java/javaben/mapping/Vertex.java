package javaben.mapping;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Vertex {
	private int id;
	private List<Edge> edges;
	private List<Vertex> adjacents;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vertex vertex = (Vertex) o;

		return id == vertex.id;
	}

	@Override
	public int hashCode() {
		return 13 + id * 37;
	}
}
