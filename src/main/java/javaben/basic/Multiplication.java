package javaben.basic;

import javaben.Callable;

public class Multiplication implements Callable {

    @Override
    public void compute(long n) {
        long a = 0;
        for (int i = 0; i < n; i++) {
            a = a * i;
        }
    }
}
