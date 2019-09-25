package javaben;

import javaben.basic.Addition;
import javaben.basic.Empty;
import javaben.basic.Multiplication;
import javaben.sort.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        long n = (long) Math.pow(10, 4);
        int size = (int) Math.pow(2, 6);
        long seed = System.currentTimeMillis();

        List<Callable> list = new ArrayList<>();
        list.add(new Addition());
        list.add(new Multiplication());

        for (Callable item : list) {
            long total = 0;
            total = total + Benchmark.bench(item, n, size, seed);
            total = total - Benchmark.bench(new Empty(), n, size, seed);
            System.out.format("%-20s%-30s%s%n", item.getClass().getSimpleName() + " :", "total (ns) : " + total, "per op (ns) : " + total / (double) n);
        }

        list.clear();

        list.add(new FusionSort());
        list.add(new HeapSort());
        list.add(new InsertionSort());
        list.add(new NativeSort());
        list.add(new QuickSort());
        list.add(new SelectionSort());
        list.add(new SmoothSort());

        for (Callable item : list) {
            long total = 0;
            total = total + Benchmark.bench(item, n, size, seed);
            total = total - Benchmark.bench(new EmptySort(), n, size, seed);
            System.out.format("%-20s%-30s%s%n", item.getClass().getSimpleName() + " :", "total (ns) : " + total, "per op (ns) : " + total / (double) n);

        }
    }
}
