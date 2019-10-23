package javaben.mapping.solver;

import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;

import java.util.List;

public class FaceSolver extends Solver {


	@Override
	String solve(Network network) {
		List<Integer> list = ((VertexListNetwork)network).getWeightedVertex();
		int offset = network.offset();

		//super.build(list.get(0), offset, offset);

		return "";
	}
}
