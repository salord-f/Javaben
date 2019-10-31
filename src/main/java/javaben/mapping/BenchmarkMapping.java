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
import java.util.Objects;

public class BenchmarkMapping {

	public static void benchmark(String alg, boolean opt) {
		try {
			launch(alg, opt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void launch(String alg, boolean opt) throws IOException {
		File folder = new File("src/main/resources/mapping/");

		Truple<Network, Solver> conf;

		switch (alg) {
			case "line":
				conf = new Truple<>(new EdgeListNetwork(), new LineSolver(), opt);
				break;
			case "snail":
				conf = new Truple<>(new EdgeListNetwork(), new SnailSolver(), opt);
				break;
			case "closest":
				conf = new Truple<>(new VertexListNetwork(), new ClosestSolver(), opt);
				break;
			case "wide":
				conf = new Truple<>(new VertexListNetwork(), new SimpleWideGreedySolver(), opt);
				break;
			case "tight":
				conf = new Truple<>(new VertexListNetwork(), new SimpleTightGreedySolver(), opt);
				break;
			case "bfs":
				conf = new Truple<>(new VertexListNetwork(), new GreedyNeighborBFSSolver(), opt);
				break;
			case "dfs":
				conf = new Truple<>(new VertexListNetwork(), new GreedyNeighborDFSSolver(), opt);
				break;
			default:
				throw new IllegalArgumentException(String.format("Algorithm %s does not exist!", alg));
		}



		for (File file : Objects.requireNonNull(folder.listFiles())) {
			String filename = ("results_" + alg + "_" + opt + "/" + file.getName().substring(2, file.getName().length() - 2) + "ans").toLowerCase();
			File oldFile = new File(filename);
			if (oldFile.exists()) {
				oldFile.delete();
			}

			System.out.println(file.getName());

			String input = FileReader.getFileAsString(file.getAbsolutePath());

			Network network = conf.left;
			Solver solver = conf.right;

			network.parseNetwork(input);
			solver.setup(network, conf.isBool());

			String output = solver.solve();

			solver.clean();
			network.clean();

			long score = new ScoreComputer(input).computeScore(output);

			System.out.format("%-40s" +
							"%-40s" +
							"%n",
					conf.right.getClass().getSimpleName() + " :",
					"score : " + score);
			FileWriter.writeOutputToFile(filename, output);
		}
	}
}
