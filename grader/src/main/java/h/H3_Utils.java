package h;

import java.util.Random;

public interface H3_Utils {

    static double[] createDoubleArray(Random random, int minSize, int maxSize) {
        return random.doubles(minSize + random.nextInt(maxSize + 1 - minSize)).toArray();
    }

    static double[] createDoubleArray(Random random) {
        return createDoubleArray(random, 1, 10);
    }

    static double[] createEmptyOutput(double[] input) {
        return new double[input.length];
    }

}
