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
		list.add(new Truple<>(new VertexListNetwork(), new SimpleWideTreeSolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new SimpleTightTreeSolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new NeighborBFSTreeSolver(), false));
		list.add(new Truple<>(new VertexListNetwork(), new NeighborDFSTreeSolver(), false));

		list.add(new Truple<>(new EdgeListNetwork(), new LineSolver(), true));
		list.add(new Truple<>(new EdgeListNetwork(), new SnailSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new ClosestSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new SimpleWideTreeSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new SimpleTightTreeSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new NeighborBFSTreeSolver(), true));
		list.add(new Truple<>(new VertexListNetwork(), new NeighborDFSTreeSolver(), true));


		for (Truple item : list) {
			String filename = ("results/" + item.right.getClass().getSimpleName() + "_" + item.isBool() + ".si5").toLowerCase();
			File oldFile = new File(filename);
			if (oldFile.exists()) {
				oldFile.delete();
			}
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
					solver.setup(network, item.isBool());

					start = System.nanoTime();
					output = solver.solve();
					time += System.nanoTime() - start;

					solver.clean();
					network.clean();
				}

				long totalTime = time / iterations;
				int score = new ScoreComputer(input).computeScore(output);

				System.out.format("%-40s" +
								"%-40s" +
								"%-40s" +
								"%-40s" +
								"%n", item.right.getClass().getSimpleName() + " :",
						"total (iterations) : " + iterations,
						"total (time) : " + totalTime,
						"score : " + score);
				FileWriter.writeMappingToFile(item.right.getClass().getSimpleName(), item.isBool(), file.getName(), totalTime, score);
			}
		}
	}
}
