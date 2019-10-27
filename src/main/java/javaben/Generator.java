package javaben;

import java.util.*;

public class Generator {
    private final static int MAX = 65535;
    private Random random;

    public Generator(long seed) {
        random = new Random(seed);
    }

    public List<Integer> unsortedListGenerator(int length) {
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            randomList.add(random.nextInt(length)); // /!\ set to MAX for Counting Sort
        }
        return randomList;
    }

    public List<Integer> unsortedSetGenerator(int length) {
        Set<Integer> randomList = new HashSet<>();
        for (int i = 0; i < length; i++) {
            randomList.add(random.nextInt(length)); // /!\ set to MAX for Counting Sort
        }
        return new ArrayList<>(randomList);
    }

    public List<Integer> sortedListGenerator(int length, boolean ascending) {
        List<Integer> sortedList = new ArrayList<>();
        if (ascending) {
            for (int i = 0; i < length; i++) {
                sortedList.add(i);
            }
        } else {
            for (int i = length - 1; i >= 0; i--) {
                sortedList.add(i);
            }
        }
        return sortedList;
    }

    public enum Type {
        UNSORTED, UNSORTEDSET, SORTEDASC, SORTEDDESC
    }
}
