package reference;

import student.ReturnData_STUD;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StrangeThings_REF {

    public static int computeStrangeValueRef(int n) {
        return n <= 0 ? 1 : n % 2 == 0 ? 1 + computeStrangeValueRef(n - 1) : 1 + computeStrangeValueRef(n - 3);
    }

    public static int whatTheHellIsThatRef(int m, int n) {
        return 0;
    }

    public static double[] transformArray_REF(double[] in) {
        return stream(in).map(d -> 2 * d + 3.14).toArray();
    }

    public static Void transformArray_REF(double[] in, double[] out) {
        assertNotNull(in, "input array is null");
        assertNotNull(out, "output array is null");
        assertEquals(in.length, out.length, "length of output array differs");
        System.arraycopy(in, 0, out, 0, in.length);
        return null;
    }

    public static int evaluateRef(char[] in) {
        var r = evaluateRecursively_REF(in, 0);
        if (r == null) {
            return Integer.MIN_VALUE;
        }
        return r.getResult();
    }

    public static ReturnData_STUD evaluateRecursively_REF(char[] in, int i) {
        char c = in[i];
        if (Character.isDigit(c)) {
            return new ReturnData_STUD(Character.getNumericValue(c), i + 1);
        }
        ReturnData_STUD s1 = evaluateRecursively_REF(in, i + 1);
        ReturnData_STUD s2 = evaluateRecursively_REF(in, s1.getNextIndex());
        if (c == '-') {
            return new ReturnData_STUD(s1.getResult() - s2.getResult(), s2.getNextIndex());
        }
        return null;
    }

}
