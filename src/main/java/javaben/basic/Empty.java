package javaben.basic;

import javaben.Callable;

public class Empty implements Callable {

    @Override
    public void init() {
        // empty
    }

    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            continue;
        }
    }
}
