package javaben.sort;

import javaben.Callable;
import javaben.Generator;
import javaben.Result;
import javaben.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkSort {


    public static void benchmarkSort(long maxPow, long seed) {
        for (int i = 0; i <= maxPow; i++) {
            int size = (int) Math.pow(2, i);
            System.out.println("size : " + size + " ");
            launch(seed, size);
        }
    }

    public static Result benchOne(Callable callable, String method, long seconds, int size, long seed, Generator.Type type) {
        callable.init(size, seed, type);
        return callable.compute(method, seconds);
    }


    private static void launch(long seed, int size) {

        long seconds = 10;

        List<Callable> list = new ArrayList<>();
        list.add(new CountingSort());
        list.add(new HeapSort());
        list.add(new InsertionSort());
        list.add(new QuickSort());
        list.add(new MergeSort());
        list.add(new NativeSort());
        list.add(new SelectionSort());

        for (Callable item : list) {
            for (Generator.Type type : Generator.Type.values()) {
                Result result = BenchmarkSort.benchOne(item, null, seconds, size, seed, type);
                long time = result.time / result.iterations;
                System.out.format("%-20s%-40s%s%n", item.getClass().getSimpleName() + " :", "total (iterations) : " + result.iterations, "total (time) : " + time);
                FileWriter.writeToFile(item.getClass().getSimpleName(), "", type.name(), size, time);
            }
        }
    }

}
