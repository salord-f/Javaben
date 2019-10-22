package javaben.mapping;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScoreComputerTest {

    @Test
    public void simpleInputTest() {
        String input = "5 5\n0 4\n1 0\n2 1\n3 2\n4 3\n";
        String output1 = "0 0\n0 1\n0 0\n1 1\n1 0\n";
        String output2 = "0 0\n0 1\n1 2\n1 1\n1 0\n";

        ScoreComputer scoreComputer = new ScoreComputer(input);

        assertEquals(6, scoreComputer.computeScore(output1));
        assertEquals(6, scoreComputer.computeScore(output2));
    }

    @Test(expected = IllegalStateException.class)
    public void invalidInputTest1() {
        String input = "5 6\n0 4\n1 0\n2 1\n3 2\n4 3\n";

        new ScoreComputer(input);
    }

    @Test(expected = IllegalStateException.class)
    public void invalidInputTest2() {
        String input = "5 4\n0 4\n1 0\n2 1\n3 2\n4 3\n";

        new ScoreComputer(input);
    }

    @Test(expected = IllegalStateException.class)
    public void invalidOutputTest1() {
        String input = "5 5\n0 4\n1 0\n2 1\n3 2\n4 3\n";
        String output = "0 0\n0 0\n0 1\n1 1\n1 0\n";

        ScoreComputer scoreComputer = new ScoreComputer(input);

        scoreComputer.computeScore(output);
    }

    @Test(expected = IllegalStateException.class)
    public void invalidOutputTest2() {
        String input = "5 5\n0 4\n1 0\n2 1\n3 2\n4 3\n";
        String output = "0 0\n0 1\n0 0\n1 1\n";

        ScoreComputer scoreComputer = new ScoreComputer(input);

        scoreComputer.computeScore(output);
    }

    @Test
    public void interestingOutputTest() {
        String input = "5 5\n0 4\n1 0\n2 1\n3 2\n4 3\n";
        String output = "0 1\n1 1\n1 2\n0 2\n0 0";

        ScoreComputer scoreComputer = new ScoreComputer(input);

        assertEquals(6, scoreComputer.computeScore(output));
    }
}
