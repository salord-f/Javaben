package javaben;

import javaben.basic.Addition;
import javaben.basic.Multiplication;
import javaben.sort.*;
import javaben.structure.immutable.*;
import javaben.structure.mutable.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		for (String arg : args) {
			System.out.println(arg);
		}

		if (args.length < 2) {
			System.out.println("Wrong number of arguments, expected structure name and method call");
			return;
		}

		long seed = 516310114;

		File folder = new File("./results/");
		if (!folder.exists()) {
			folder.mkdir();
		}

		for (File f : folder.listFiles()) {
			if ((args[0] + "_" + args[1] + "_" + args[2] + ".si5").equals(f.getName())) {
				f.delete();
			}
		}

		long seconds = 10;

		int maxPow = 20;

		List<Callable> list = new ArrayList<>();
		list.add(new Addition());
		list.add(new Multiplication());

		// Warmup
		for (Callable item : list) {
			Result result = Benchmark.bench(item, null, seconds, 1, seed, Generator.Type.UNSORTED);
			System.out.format("%-20s%-40s%s%n", item.getClass().getSimpleName() + " :", "total (iterations) : " + result.iterations, "total (time) : " + result.time / result.iterations);
		}

		benchmarkStructure(seed, maxPow, args);

		// Uncomment for sorting algorithms
		/*for (int i = 0; i <= maxPow; i++) {
			int size = (int) Math.pow(2, i);
			System.out.println("size : " + size + " ");
			launch(seed, size);
		}*/

	}

	private static void writeToFile(String name, String initType, String method, long size, long time) {
		String filename = name + "_" + initType + "_" + method + ".si5";
		Path path = Paths.get("results/" + filename.toLowerCase());

		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(size + " " + time);
		} catch (Exception e) {
			System.out.println("Error while writing file : " + e.getMessage());
		}
	}

	private static void benchmarkStructure(long seed, int maxPow, String[] args) {
		long seconds = 10;

		Callable callable = null;

		for (int i = 0; i <= maxPow; i++) {
			int size = (int) Math.pow(2, i);
			switch (args[0]) {
				case "MutableArray":
					callable = new MutableArray(size);
					break;
				case "MutableAVL":
					callable = new MutableAVL();
					break;
				case "MutableHeap":
					callable = new MutableHeap();
					break;
				case "MutableQueue":
					callable = new MutableQueue();
					break;
				case "MutableStack":
					callable = new MutableStack();
					break;
				case "ImmutableArray":
					callable = new ImmutableArray(size);
					break;
				case "ImmutableAVL":
					callable = new ImmutableAVL();
					break;
				case "ImmutableHeap":
					callable = new ImmutableHeap();
					break;
				case "ImmutableQueue":
					callable = new ImmutableQueue();
					break;
				case "ImmutableStack":
					callable = new ImmutableStack();
					break;
				default:
					throw new UnsupportedOperationException();
			}
			for (int j = 2; j < args.length; j++) {
				Generator.Type type = Generator.Type.valueOf(args[j]);
				Result result = Benchmark.benchStructure(callable, args[1], seconds, size, seed, type);
				long time = result.time / result.iterations;
				System.out.format("%-20s%-40s%s%n", args[0] + " :", "total (iterations) : " + result.iterations, "total (time) : " + time);
				writeToFile(callable.getClass().getSimpleName(), args[1], type.name(), size, time);
			}
		}
	}

	private static void launch(long seed, int size) {

		long seconds = 10;

		List<Callable> list = new ArrayList<>();
		list.add(new CountingSort());
		list.add(new HeapSort());
		list.add(new InsertionSort());
		list.add(new QuickSort());
		list.add(new MergeSort());
		list.add(new NativeSort());
		list.add(new SelectionSort());

		for (Callable item : list) {
			for (Generator.Type type : Generator.Type.values()) {
				Result result = Benchmark.bench(item, null, seconds, size, seed, type);
				long time = result.time / result.iterations;
				System.out.format("%-20s%-40s%s%n", item.getClass().getSimpleName() + " :", "total (iterations) : " + result.iterations, "total (time) : " + time);
				writeToFile(item.getClass().getSimpleName(), "", type.name(), size, time);
			}
		}
	}

}
