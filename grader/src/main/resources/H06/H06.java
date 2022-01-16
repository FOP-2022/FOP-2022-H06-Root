package Solution_H06;

public class Solution_H06 {

	public static int computeStrangeValue1(int n) {
		return n <= 0 ? 1 : n % 2 == 0 ? 1 + computeStrangeValue1(n - 1) : 1 + computeStrangeValue1(n - 3);
	}

	public static int computeStrangeValue2(int n) {
		if (n <= 0)
			return 1;
		else if (0 == n % 2)
			return 1 + computeStrangeValue2(n - 1);
		else
			return 1 + computeStrangeValue2(n - 3);
	}

	public static int whatTheHellIsThat1(int m, int n) {
		return m <= 0 || n <= 0 ? 0 : m < n ? whatTheHellIsThat1(1 + m, n - m) : whatTheHellIsThat1(1 + n, m - n);
	}

	public static int whatTheHellIsThat2(int m, int n) {
		if (m <= 0 || n <= 0)
			return 0;
		else if (m < n)
			return whatTheHellIsThat2(1 + m, n - m);
		else
			return whatTheHellIsThat2(1 + n, m - n);
	}

	public static double[] transformedArrayIteratively(double[] array, Direction direction) {
		double[] result = new double[array.length];
		for (int i = direction == Direction.UP ? 0 : array.length - 1; 0 <= i && i < array.length; i += (direction == Direction.UP ? 1 : -1))
			result[i] = 3.14 + 2 * array[i];
		return result;
	}

	public static double[] transformedArrayRecursively(double[] array, Direction direction) {
		double[] result = new double[array.length];
		return doTheRecursion(array, result, direction, direction == Direction.UP ? 0 : array.length - 1);
	}

	private static double[] doTheRecursion(double[] input, double[] output, Direction direction, int i) {
		if (i < 0 || i >= input.length)
			return output;
		output[i] = 3.14 + 2 * input[i];
		return doTheRecursion(input, output, direction, i + (direction == Direction.UP ? 1 : -1));
	}

	public static int evaluate(String string) {
		return evaluate(string.toCharArray());
	}

	public static int evaluate(char[] chars) {
		return evaluateRecursively(chars, 0).result;
	}

	private static ReturnStruct evaluateRecursively(char[] characters, int i) {
		char character = characters[i];
		if (Character.isDigit(character))
			return new ReturnStruct(Character.getNumericValue(character), i+1);
		ReturnStruct s1 = evaluateRecursively(characters, i+1);
		ReturnStruct s2 = evaluateRecursively(characters, s1.indexOfRest);
		if (character == '+')
			return new ReturnStruct(s1.result + s2.result, s2.indexOfRest);
		if (character == '-')
			return new ReturnStruct(s1.result - s2.result, s2.indexOfRest);
		return null;
	}

	private static class ReturnStruct {

		public int result, indexOfRest;

		public ReturnStruct() {

		}

		public ReturnStruct(int result, int indexOfRest) {
			this.result = result;
			this.indexOfRest = indexOfRest;
		}

	}

}
