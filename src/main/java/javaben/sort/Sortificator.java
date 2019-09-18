package javaben.sort;

import javaben.Callable;

import java.util.ArrayList;
import java.util.List;

public class Sortificator implements Callable {
    /**
     * @param unsortedList list of positive integers
     * @return greatest to smallest
     */
    public static List<Integer> selectionSort(List<Integer> unsortedList) {
        List<Integer> res = new ArrayList<>();
        while (unsortedList.size() > 0) {
            int max = -1;
            int max_index = -1;
            for (int j = 0; j < unsortedList.size(); j++) {
                if (unsortedList.get(j) > max) {
                    max = unsortedList.get(j);
                    max_index = j;
                }
            }
            res.add(max);
            unsortedList.remove(max_index);
        }
        return res;
    }
    public static List<Integer> insertionSort(List<Integer> unsortedList) {
        List<Integer> res = new ArrayList<>();
        while (unsortedList.size() > 0) {

        }
        return res;
    }
    public static List<Integer> fusionSort(List<Integer> unsortedList) {
        return null;
    }
    public static List<Integer> heapSort(List<Integer> unsortedList) {
        return null;
    }
    public static List<Integer> quickSort(List<Integer> unsortedList) {
        return null;
    }
    public static List<Integer> smoothSort(List<Integer> unsortedList) {
        return null;
    }
    public static List<Integer> nativeSort(List<Integer> unsortedList) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void compute(long n) {

    }
}
