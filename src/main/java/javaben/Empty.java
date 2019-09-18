package javaben;

public class Empty implements Callable {

    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            continue;
        }
    }
}
