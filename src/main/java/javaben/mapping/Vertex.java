package javaben.mapping;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Vertex {
	private int id;
	private List<Edge> edges;
	private List<Vertex> adjacents;
}
