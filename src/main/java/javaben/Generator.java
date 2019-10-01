package javaben;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
	private final static int MAX = 65535;
	private Random random;

	public Generator(long seed) {
		random = new Random(seed);
	}

	public List<Integer> unsortedListGenerator(int length) {
		List<Integer> randomList = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			randomList.add(random.nextInt(MAX));
		}
		return randomList;
	}

	public List<Integer> sortedListGenerator(int length, boolean ascending) {
		List<Integer> sortedList = new ArrayList<>();
		if (ascending) {
			for (int i = 0; i < length; i++) {
				sortedList.add(i);
			}
		} else {
			for (int i = length - 1; i >= 0; i--) {
				sortedList.add(i);
			}
		}
		return sortedList;
	}

	public enum Type {
		UNSORTED, SORTEDASC, SORTEDDESC
	}
}
