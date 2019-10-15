package javaben.structure;

import javaben.Callable;
import javaben.Generator;
import javaben.Result;

import java.util.ArrayList;
import java.util.List;

public abstract class Structure implements Callable {
	private List<Integer> list = new ArrayList<>();

	public abstract void setup(String method, List<Integer> source);

	@Override
	public List<Integer> init(int size, long seed, Generator.Type type) {
		switch (type) {
			case UNSORTED:
				list = new Generator(seed).unsortedListGenerator(size);
				break;
			case UNSORTEDSET:
				list = new Generator(seed).unsortedSetGenerator(size);
				break;
			case SORTEDASC:
				list = new Generator(seed).sortedListGenerator(size, true);
				break;
			case SORTEDDESC:
				list = new Generator(seed).sortedListGenerator(size, false);
				break;
		}
		return list;
	}

	@Override
	public Result compute(String method, long seconds) {
		long current = System.nanoTime();
		int iterations = 0;
		while (System.nanoTime() - current < seconds * 1000000000) {
			iterations++;
			method(method, list);
		}
		return new Result(System.nanoTime() - current, iterations);
	}

	public abstract void method(String method, List<Integer> source);

}
