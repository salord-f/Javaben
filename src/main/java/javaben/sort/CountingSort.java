package javaben.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountingSort extends Sort {

	private final static int MAX = 65535;

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
