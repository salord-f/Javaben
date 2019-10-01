package javaben;

import javaben.basic.Addition;
import javaben.basic.Empty;
import javaben.basic.Multiplication;
import javaben.sort.*;

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
		long seed = 516310114;

		File folder = new File("./results/");
		if (!folder.exists()) {
			folder.mkdir();
		}

		for (File f : folder.listFiles()) {
			if (f.getName().endsWith(".si5")) {
				f.delete(); // may fail mysteriously - returns boolean you may want to check
			}
		}

		long iterations = (long) Math.pow(10, 10);


		int maxPow = 20;

		List<Callable> list = new ArrayList<>();
		list.add(new Addition());
		list.add(new Multiplication());

		// Warmup
		for (Callable item : list) {
			long total = 0;
			total = total + Benchmark.bench(item, iterations, 1, seed, Generator.Type.UNSORTED);
			total = total - Benchmark.bench(new Empty(), iterations, 1, seed, Generator.Type.UNSORTED);
			System.out.format("%-20s%-30s%s%n", item.getClass().getSimpleName() + " :", "total (ns) : " + total, "per op (ns) : " + total / (double) iterations);
		}

		for (int i = 0; i < maxPow; i++) {
			int size = (int) Math.pow(2, i);
			System.out.println("size : " + size + " ");
			launch(seed, size);
		}

	}

	private static void launch(long seed, int size) {

		long iterations = (long) Math.pow(10, 4);

		List<Callable> list = new ArrayList<>();
		list.add(new CountingSort());
		list.add(new HeapSort());
		list.add(new InsertionSort());
		list.add(new QuickSort());
		list.add(new MergeSort());
		list.add(new NativeSort());
		list.add(new SelectionSort());
		//list.add(new SmoothSort());

		for (Callable item : list) {
			for (Generator.Type type : Generator.Type.values()) {
				long total = 0;
				total = total + Benchmark.bench(item, iterations, size, seed, type);
				total = total - Benchmark.bench(new EmptySort(), iterations, size, seed, type);
				System.out.format("%-20s%-30s%s%n", item.getClass().getSimpleName() + " :", "total (ns) : " + total, "per op (ns) : " + total / (double) iterations);
				writeToFile(item.getClass().getSimpleName(), type.name(), size, total / 1000000);
			}
		}
	}

	private static void writeToFile(String name, String initType, long size, long time) {
		String filename = name + "_" + initType + ".si5";
		Path path = Paths.get("results/" + filename.toLowerCase());

		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(size + " " + time);
		} catch (Exception e) {
			System.out.println("Error while writing file : " + e.getMessage());
		}
	}

	private double factor(int size, long time) {
		double sizeLog = Math.log(size);
		double timeLog = Math.log(time);
		return 0;
	}
}
