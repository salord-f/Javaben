package javaben.mapping;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class StupidSolverTest {

	@Test
	public void stupidTest() {
		StupidSolver solver = new StupidSolver();
		EdgeListNetwork network = new EdgeListNetwork();
		network.readFile("src/main/resources/mapping/GG16.in");
		String output = solver.solve(network);
		ScoreComputer computer = new ScoreComputer();
		assertTrue(computer.computeScore(output) > 0);
	}
}
