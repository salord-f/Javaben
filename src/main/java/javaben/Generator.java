package javaben;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private Random random = new Random();

    public List<Integer> unsortedListGenerator(int length, int max) {
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            randomList.add(random.nextInt(max));
        }
        return randomList;
    }


}
