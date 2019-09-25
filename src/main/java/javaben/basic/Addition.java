package javaben.basic;

public class Addition extends Basic {

    @Override
    public void compute(long n) {
        long a = 0;
        for (int i = 0; i < n; i++) {
            a += 7;
        }
    }
}
