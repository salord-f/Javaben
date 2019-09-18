package javaben.basic;

import javaben.Callable;

public class Addition implements Callable {

    @Override
    public void init() {
        // empty
    }

    @Override
    public void compute(long n) {
        long a = 0;
        for (int i = 0; i < n; i++) {
            a += 7;
        }
    }
}
