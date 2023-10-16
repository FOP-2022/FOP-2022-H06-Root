package h06;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;
import static java.util.stream.IntStream.range;

public class RandomTraverser implements Traverser {

    private final int first;
    private final Map<Integer, Integer> map = new HashMap<>();

    public RandomTraverser(int size) {
        Random random = new Random();
        LinkedList<Integer> rest = range(0, size).boxed().collect(Collectors.toCollection(LinkedList::new));
        shuffle(rest, random);
        Integer last = first = rest.removeFirst(), current;
        while (!rest.isEmpty()) {
            current = rest.removeFirst();
            map.put(last, current);
            last = current;
        }
        map.put(last, random.nextBoolean() ? -1 : size);
    }

    @Override
    public int getFirstIndex(double[] array) {
        return first;
    }

    @Override
    public int getNextIndex(int index) {
        return map.get(index);
    }

}
