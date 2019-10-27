package javaben.mapping.solver;

import javaben.mapping.ScoreComputer;
import javaben.mapping.network.VertexListNetwork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleTreeSolverTest {

    @Test
    public void simpleInputTest() {
        String input = "5 5\n0 4\n1 0\n2 1\n3 2\n4 3\n";
        SimpleTreeSolver solver = new SimpleTreeSolver();
        VertexListNetwork network = new VertexListNetwork();
        network.parseNetwork(input);
        String output = solver.solve(network);
        ScoreComputer computer = new ScoreComputer(input);
        assertEquals(6, computer.computeScore(output));
    }
}
