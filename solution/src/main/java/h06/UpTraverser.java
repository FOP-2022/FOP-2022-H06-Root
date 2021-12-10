package h06;

public class UpTraverser implements Traverser {

    @Override
    public int getFirstIndex(double[] array) {
        return 0;
    }

    @Override
    public int getNextIndex(int currentIndex) {
        return currentIndex + 1;
    }
}
