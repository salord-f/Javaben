package javaben.mapping.solver;

import javaben.io.FileReader;
import javaben.mapping.ScoreComputer;
import javaben.mapping.network.VertexListNetwork;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Ignore
public class FaceSolverTest {

    @Test
    public void simpleInputTest() {
        String input = "5 5\n0 4\n1 0\n2 1\n3 2\n4 3\n";
        FaceSolver solver = new FaceSolver();
        VertexListNetwork network = new VertexListNetwork();
        network.parseNetwork(input);
        String output = solver.solve(network);
        ScoreComputer computer = new ScoreComputer(input);
        assertEquals(6, computer.computeScore(output));
    }

    @Test
    public void complexInputTest() {
        String input = "20 36\n0 1\n0 6\n0 11\n0 15\n1 3\n1 4\n1 19\n2 5\n2 7\n2 12\n2 13\n3 15\n3 16\n3 17\n4 6\n4 14\n4 18\n5 6\n5 8\n5 11\n6 12\n7 14\n7 17\n7 18\n8 12\n9 11\n9 15\n10 14\n10 18\n11 13\n12 14\n13 15\n13 17\n16 19\n17 19\n18 19";
        FaceSolver solver = new FaceSolver();
        VertexListNetwork network = new VertexListNetwork();
        network.parseNetwork(input);
        String output = solver.solve(network);
        ScoreComputer computer = new ScoreComputer(input);
        assertEquals(637, computer.computeScore(output));

    }

    @Test
    public void completeInputTest() throws IOException {
        String input = FileReader.getFileAsString("src/main/resources/mapping/CP16.in");
        FaceSolver solver = new FaceSolver();
        VertexListNetwork network = new VertexListNetwork();
        network.parseNetwork(input);
        String output = solver.solve(network);
        System.out.println(output);
        ScoreComputer computer = new ScoreComputer(input);
        assertEquals(1574, computer.computeScore(output));
    }
}
