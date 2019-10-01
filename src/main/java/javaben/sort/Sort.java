package javaben.sort;

import javaben.Callable;
import javaben.Generator;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort implements Callable {
	public boolean testing = false;

	private List<Integer> list = new ArrayList<>();

	@Override
	public void init(int size, long seed, Generator.Type type) {
		switch (type) {
			case UNSORTED:
				list = new Generator(seed).unsortedListGenerator(size);
				break;
			case SORTEDASC:
				list = new Generator(seed).sortedListGenerator(size, true);
				break;
			case SORTEDDESC:
				list = new Generator(seed).sortedListGenerator(size, false);
				break;
		}
	}

	@Override
	public void compute(long n) {
		for (long i = 0; i < n; i++) {
			List<Integer> copiedList = new ArrayList<>(list);
			sort(copiedList);
		}
	}

	public abstract List<Integer> sort(List<Integer> source);
}
