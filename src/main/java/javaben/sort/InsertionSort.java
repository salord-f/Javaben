package javaben.sort;

import java.util.List;

public class InsertionSort extends Sort {

    @Override
    public List<Integer> sort(List<Integer> source) {
        for (int j = 1; j < source.size(); j++) {
            int k = j - 1;
            while (k >= 0 && source.get(k) < source.get(j)) {
                k--;
            }
            if (j != (k + 1)) {
                int tmp = source.get(j);
                source.remove(j);
                source.add(k + 1, tmp);
            }
        }
        return source;
    }
}
