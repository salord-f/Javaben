package javaben.mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Vertex {
	private int id;
	private List<Edge> edges;
	private List<Vertex> neighbours;

}
