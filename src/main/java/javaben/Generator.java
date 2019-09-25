package javaben;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private Random random;
    private final static int MAX = 65535;

    public Generator(long seed) {
        random = new Random(seed);
    }

    public List<Integer> unsortedListGenerator(int length) {
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            randomList.add(random.nextInt(MAX));
        }
        return randomList;
    }

    public List<Integer> sortedListGenerator(int length, String order) {
        List<Integer> sortedList = new ArrayList<>();
        switch (order) {
            case "asc":
                for (int i = 0; i < length; i++) {
                    sortedList.add(i);
                }
                break;
            case "desc":
                for (int i = length - 1; i >= 0; i--) {
                    sortedList.add(i);
                }
                break;
            default:
                throw new IllegalArgumentException(String.format("%s is not a valid order", order));
        }
        return sortedList;
    }
}
