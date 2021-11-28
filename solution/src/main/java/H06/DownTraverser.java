package H06;

public class DownTraverser implements Traverser {

	@Override
	public int getFirstIndex(double[] array) {
		return array.length - 1;
	}

	@Override
	public int getNextIndex(int currentIndex) {
		return currentIndex - 1;
	}
}
