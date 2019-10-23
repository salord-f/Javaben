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

}
