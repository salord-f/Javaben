package javaben.sort;

import java.util.ArrayList;
import java.util.List;

public class CountingSort extends Sort {

	private final static int MAX = 65535;

	// https://www.geeksforgeeks.org/counting-sort/
	@Override
	public List<Integer> sort(List<Integer> source) {
		int[] array = new int[MAX + 1];
		List<Integer> output = new ArrayList<>();

		for (Integer integer : source) {
			array[integer]++;
		}

		for (int j = 0; j < MAX + 1; j++) {
			for (int i = 0; i < array[j]; i++) {
				output.add(j);
			}
		}

		return output;
	}
}
