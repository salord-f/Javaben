package javaben;

import javaben.basic.Addition;
import javaben.basic.Multiplication;
import javaben.sort.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        long n = (long) Math.pow(10, 4);
        List<Callable> list = new ArrayList<>();
        list.add(new Addition());
        list.add(new Multiplication());
        list.add(new FusionSort());
        list.add(new HeapSort());
        list.add(new InsertionSort());
        list.add(new NativeSort());
        list.add(new QuickSort());
        list.add(new SelectionSort());
        list.add(new SmoothSort());

        for (Callable item : list) {
            long total = 0;
            total = total + Benchmark.bench(item, n);
            total = total - Benchmark.bench(new EmptySort(), n);
            System.out.format("%-30s%-30s%s%n", item.getClass().getSimpleName() + " :", "total : " + total + "ns", "per op : " + total / (double) n + "ns");

        }
    }
}
