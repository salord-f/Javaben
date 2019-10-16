package javaben;

import javaben.basic.Addition;
import javaben.basic.Multiplication;
import javaben.sort.BenchmarkSort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		for (String arg : args) {
			System.out.println(arg);
		}

		if (args.length >= 2) {
			System.out.println("Wrong number of arguments, expected structure name and method call");
			File folder = new File("./results/");
			if (!folder.exists()) {
				folder.mkdir();
			}

			for (File f : folder.listFiles()) {
				if ((args[0] + "_" + args[1] + "_" + args[2] + ".si5").toLowerCase().equals(f.getName())) {
					f.delete();
				}
			}
		}

		long seed = 516310114;

		long seconds = 10;

		int maxPow = 20;

		List<Callable> list = new ArrayList<>();
		list.add(new Addition());
		list.add(new Multiplication());

		// Warmup
		for (Callable item : list) {
			Result result = BenchmarkSort.benchOne(item, null, seconds, 1, seed, Generator.Type.UNSORTED);
			System.out.format("%-20s%-40s%s%n", item.getClass().getSimpleName() + " :", "total (iterations) : " + result.iterations, "total (time) : " + result.time / result.iterations);
		}

		//BenchmarkSort.benchmarkSort(maxPow, seed);
		//BenchmarkStructure.benchmarkStructure(seed, maxPow, args);


	}


}
