package javaben.basic;

public class Empty extends Basic {

    @Override
    public void compute(long n) {
        for (long i = 0; i < n; i++) {
            continue;
        }
    }
}
