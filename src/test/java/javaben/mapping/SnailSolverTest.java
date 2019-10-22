package javaben.mapping;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnailSolverTest {

	@Test
	public void simpleInputTest() {
		String input = "5 5\n0 4\n1 0\n2 1\n3 2\n4 3\n";
		SnailSolver solver = new SnailSolver();
		EdgeSetNetwork network = new EdgeSetNetwork();
		network.parseNetwork(input);
		String output = solver.solve(network);
		ScoreComputer computer = new ScoreComputer(input);
		assertEquals(6, computer.computeScore(output));
	}
}
