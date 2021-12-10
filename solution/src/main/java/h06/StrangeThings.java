package h06;

public class StrangeThings {

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

    public static double[] transformArrayIteratively(double[] in, Traverser t) {
        double[] result = new double[in.length];
        for (int i = t.getFirstIndex(in); 0 <= i && i < in.length; i = t.getNextIndex(i))
            result[i] = 3.14 + 2 * in[i];
        return result;
    }

    public static double[] transformArrayRecursively(double[] in, Traverser t) {
        double[] out = new double[in.length];
        doTheRecursion(in, out, t, t.getFirstIndex(in));
        return out;
    }

    private static void doTheRecursion(double[] in, double[] out, Traverser t, int i) {
        if (i < 0 || i >= in.length)
            return;
        out[i] = 3.14 + 2 * in[i];
        doTheRecursion(in, out, t, t.getNextIndex(i));
    }

    public static int evaluate(String in) {
        return evaluate(in.toCharArray());
    }

    public static int evaluate(char[] in) {
        return evaluateRecursively(in, 0).result;
    }

    private static ReturnData evaluateRecursively(char[] in, int i) {
        char c = in[i];
        if (Character.isDigit(c)) {
            return new ReturnData(Character.getNumericValue(c), i + 1);
        }
        ReturnData s1 = evaluateRecursively(in, i + 1);
        ReturnData s2 = evaluateRecursively(in, s1.nextIndex);
        // optional
        if (c == '+') {
            return new ReturnData(s1.result + s2.result, s2.nextIndex);
        }
        if (c == '-') {
            return new ReturnData(s1.result - s2.result, s2.nextIndex);
        }
        return null;
    }
}
