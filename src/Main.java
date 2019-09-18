public class Main {

    public static void main(String[] args) {
        long total = 0;
        for(int i = 0; i < 100; i++) {
            total += Basic.emptyFor(0);
        }
        System.out.println(total / 100);
    }
}
