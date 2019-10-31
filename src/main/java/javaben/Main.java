package javaben;

import javaben.mapping.BenchmarkMapping;
import javaben.sort.BenchmarkSort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        for (String arg : args) {
            System.out.println(arg);
        }

        String alg = args[0];
        boolean opt = Boolean.parseBoolean(args[1]);

        File folder = new File("./results_" + alg + "_" + opt + "/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        long seed = 516310114;

        long seconds = 10;

        int maxPow = 20;

        List<Callable> list = new ArrayList<>();
        //list.add(new Addition());
        //list.add(new Multiplication());

        // Warmup
        for (Callable item : list) {
            Result result = BenchmarkSort.benchOne(item, null, seconds, 1, seed, Generator.Type.UNSORTED);
            System.out.format("%-20s%-40s%s%n", item.getClass().getSimpleName() + " :", "total (iterations) : " + result.iterations, "total (time) : " + result.time / result.iterations);
        }

        //BenchmarkSort.benchmarkSort(maxPow, seed);
        //BenchmarkStructure.benchmarkStructure(seed, maxPow, args);
        BenchmarkMapping.benchmark(alg, opt);

    }


}
