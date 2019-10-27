package javaben.sort;

import java.util.Collections;
import java.util.List;

public class SelectionSort extends Sort {

    @Override
    public List<Integer> sort(List<Integer> source) {
        for (int j = 0; j < source.size(); j++) {
            int min = source.get(j);
            int min_index = j;
            for (int k = j + 1; k < source.size(); k++) {
                if (source.get(k) < min) {
                    min = source.get(k);
                    min_index = k;
                }
            }
            Collections.swap(source, j, min_index);
        }
        return source;
    }
}
