package javaben.mapping;

import javaben.io.FileReader;
import javaben.io.FileWriter;
import javaben.mapping.network.EdgeListNetwork;
import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;
import javaben.mapping.solver.*;
import javaben.structure.Tuple;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BenchmarkMapping {

	public static void benchmark() {
		try {
			launch();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void launch() throws IOException {
		File folder = new File("src/main/resources/mapping/");

		long seconds = 10;

		List<Tuple<Network, Solver>> list = new ArrayList<>();
		list.add(new Tuple<>(new EdgeListNetwork(), new StupidSolver()));
		list.add(new Tuple<>(new EdgeListNetwork(), new SnailSolver()));
		list.add(new Tuple<>(new VertexListNetwork(), new ClosestSolver()));
        list.add(new Tuple<>(new VertexListNetwork(), new SimpleWideTreeSolver()));
        list.add(new Tuple<>(new VertexListNetwork(), new SimpleTightTreeSolver()));
		list.add(new Tuple<>(new VertexListNetwork(), new NeighborBFSTreeSolver()));
		list.add(new Tuple<>(new VertexListNetwork(), new NeighborDFSTreeSolver()));


		for (Tuple item : list) {
			for (File file : Objects.requireNonNull(folder.listFiles())) {

				System.out.println(file.getName());

				long start, time = 0;
				long t0 = System.nanoTime();
				int iterations = 0;

				String input = FileReader.getFileAsString(file.getAbsolutePath());
				String output = null;

				while (System.nanoTime() - t0 < seconds * 1000000000) {
					iterations++;

					Network network = ((Network) item.left);
					Solver solver = ((Solver) item.right);

					network.parseNetwork(input);

					start = System.nanoTime();
					output = solver.solve(network);
					time += System.nanoTime() - start;

					solver.clean();
					network.clean();
				}

				long totalTime = time / iterations;
				int score = new ScoreComputer(input).computeScore(output);

				System.out.format("%-20s" +
								"%-40s" +
								"%-40s" +
								"%-40s" +
								"%n", item.right.getClass().getSimpleName() + " :",
						"total (iterations) : " + iterations,
						"total (time) : " + totalTime,
						"score : " + score);
				FileWriter.writeToFile(item.right.getClass().getSimpleName(), "", "", file.getName(), totalTime);
			}
		}
	}
}
