package javaben;

public class Basic {

    public static double emptyFor(int x) {
        long startTime = System.nanoTime();
        for (int i = 0; i < x; i++) {
            continue;
        }
        long endTime = System.nanoTime();
        long total = endTime - startTime;
        //System.out.println("Total execution time: " + total + "ns");
        return total;
    }

}
