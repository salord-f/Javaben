package javaben.sort;

import java.util.Collections;
import java.util.List;

public class SelectionSort extends Sort {

    @Override
    public List<Integer> sort(List<Integer> source) {
        for (int j = 0; j < source.size(); j++) {
            int max = -1;
            int max_index = -1;
            for (int k = j; k < source.size(); k++) {
                if (source.get(k) > max) {
                    max = source.get(k);
                    max_index = k;
                }
            }
            Collections.swap(source, j, max_index);
        }
        return source;
    }
}
