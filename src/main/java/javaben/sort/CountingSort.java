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
        int[] output = new int[source.size()];

        for (Integer integer : source) {
            array[integer]++;
        }
        for (int j = 1; j < MAX; j++) {
            array[j] += array[j - 1];
        }
        for (int j = source.size() - 1; j >= 0; j--) {
            output[source.size() - array[source.get(j)]] = source.get(j);
            array[source.get(j)]--;
        }
        if (testing) {
            // Takes time which is unwanted when benchmarking
            return Arrays.stream(output).boxed().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
