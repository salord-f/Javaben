package javaben;

import javaben.basic.Addition;
import javaben.basic.Empty;
import javaben.basic.Multiplication;
import javaben.sort.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		long seed = 516310114;


		long iterations = (long) Math.pow(10, 6);

		int maxPow = 1;

		for (int i = 0; i < maxPow; i++) {
			int size = (int) Math.pow(2, i);
			System.out.println("size : " + size + " ");
			launch(seed, size, iterations);
		}


	}

	private static void launch(long seed, int size, long iterations) {
		List<Callable> list = new ArrayList<>();
		list.add(new Addition());
		list.add(new Multiplication());

		for (Callable item : list) {
			long total = 0;
			total = total + Benchmark.bench(item, iterations, size, seed);
			total = total - Benchmark.bench(new Empty(), iterations, size, seed);
			System.out.format("%-20s%-30s%s%n", item.getClass().getSimpleName() + " :", "total (ns) : " + total, "per op (ns) : " + total / (double) iterations);
		}

		iterations = (long) Math.pow(10, 6);

		list.clear();
		list.add(new CountingSort());
		list.add(new HeapSort());
		list.add(new InsertionSort());
		list.add(new QuickSort());
		list.add(new MergeSort());
		list.add(new NativeSort());
		list.add(new SelectionSort());
		list.add(new SmoothSort());

		for (Callable item : list) {
			long total = 0;
			total = total + Benchmark.bench(item, iterations, size, seed);
			total = total - Benchmark.bench(new EmptySort(), iterations, size, seed);
			System.out.format("%-20s%-30s%s%n", item.getClass().getSimpleName() + " :", "total (ns) : " + total, "per op (ns) : " + total / (double) iterations);

		}
	}

	private double factor(int size, long time) {
		double sizeLog = Math.log(size);
		double timeLog = Math.log(time);
		return 0;
	}
}
