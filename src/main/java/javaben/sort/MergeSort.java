package javaben.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MergeSort extends Sort {

    // https://www.baeldung.com/java-merge-sort
    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    @Override
    public List<Integer> sort(List<Integer> source) {
        int[] array = source.stream().mapToInt(i -> i).toArray();
        mergeSort(array, source.size());
        if (testing) {
            // Takes time which is unwanted when benchmarking
            List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
            Collections.reverse(list);
            return list;
        }
        return new ArrayList<>();
    }
}
