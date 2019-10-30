package javaben.mapping;

import javaben.io.FileReader;
import javaben.io.FileWriter;
import javaben.mapping.network.EdgeListNetwork;
import javaben.mapping.network.Network;
import javaben.mapping.network.VertexListNetwork;
import javaben.mapping.solver.*;
import javaben.structure.Truple;

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

		List<Truple<Network, Solver>> list = new ArrayList<>();
		list.add(new Truple<>(new EdgeListNetwork(), new LineSolver(), false));
		list.add(new Truple<>(new EdgeListNetwork(), new SnailSolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new ClosestSolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new SimpleWideGreedySolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new SimpleTightGreedySolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new GreedyNeighborBFSSolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new GreedyNeighborDFSSolver(), false));

		list.add(new Truple<>(new EdgeListNetwork(), new LineSolver(), true));
		list.add(new Truple<>(new EdgeListNetwork(), new SnailSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new ClosestSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new SimpleWideGreedySolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new SimpleTightGreedySolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new GreedyNeighborBFSSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new GreedyNeighborDFSSolver(), true));


		for (File file : Objects.requireNonNull(folder.listFiles())) {
			/*String filename = ("results/" + item.right.getClass().getSimpleName() + "_" + item.isBool() + ".si5").toLowerCase();
			File oldFile = new File(filename);
			if (oldFile.exists()) {
				oldFile.delete();
			}*/

			for (Truple item : list) {
				System.out.println(file.getName());

				//long start, time = 0;
				//long t0 = System.nanoTime();
				//int iterations = 0;

				String input = FileReader.getFileAsString(file.getAbsolutePath());
				String output = null;

				//while (System.nanoTime() - t0 < seconds * 1000000000) {
				//iterations++;

				Network network = ((Network) item.left);
				Solver solver = ((Solver) item.right);

				network.parseNetwork(input);
				solver.setup(network, item.isBool());

				//start = System.nanoTime();
				output = solver.solve();
				//time += System.nanoTime() - start;

				solver.clean();
				network.clean();
				//}

				//long totalTime = time / iterations;
				int score = new ScoreComputer(input).computeScore(output);

				System.out.format("%-40s" +
								"%-40s" +
								//"%-40s" +
								//"%-40s" +
								"%n", item.right.getClass().getSimpleName() + " :",
						//"total (iterations) : " + iterations,
						//"total (time) : " + totalTime,
						"score : " + score);
				//FileWriter.writeMappingToFile(item.right.getClass().getSimpleName(), item.isBool(), file.getName(), totalTime, score);
				FileWriter.writeOutputToFile(item.right.getClass().getSimpleName(), file.getName(), score, output);
			}
		}
	}
}
