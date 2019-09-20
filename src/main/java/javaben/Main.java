package javaben;

import javaben.basic.*;
import javaben.sort.*;

public class Main {

    public static void main(String[] args) {
        long total = 0;

        long n = (long) Math.pow(10, 9);
        for (int i = 0; i < 10; i++) {
            total = total + Benchmark.bench(new InsertionSort(), n);
            total = total - Benchmark.bench(new EmptySort(), n);
        }

        System.out.println(total);
        System.out.println(total / (double) n / 10);
    }
}
