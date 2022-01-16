package student;

import org.mockito.MockedStatic;
import reflection.ClassTester;
import reflection.MethodTester;
import reflection.ParameterMatcher;
import tutor.Utils;

import java.lang.reflect.Method;
import java.util.List;

import static java.lang.reflect.Modifier.*;
import static h.Global.SIMILARITY;
import static student.ReturnData_STUD.RETURN_DATA;
import static student.Traverser_STUD.TRAVERSER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class StrangeThings_STUD {

    public static final ClassTester<?> STRANGE_THINGS = new ClassTester(
        "h06",
        "StrangeThings",
        SIMILARITY);

    public static final MethodTester WHAT_THE_HELL_IS_THAT_1 = new MethodTester(
        STRANGE_THINGS,
        "whatTheHellIsThat1",
        SIMILARITY,
        PUBLIC | STATIC,
        int.class,
        List.of(
            new ParameterMatcher(int.class),
            new ParameterMatcher(int.class)
        )
    );

    public static final MethodTester WHAT_THE_HELL_IS_THAT_2 = new MethodTester(
        STRANGE_THINGS,
        "whatTheHellIsThat2",
        SIMILARITY,
        PUBLIC | STATIC,
        int.class,
        List.of(
            new ParameterMatcher(int.class),
            new ParameterMatcher(int.class)
        )
    );



    public static final MethodTester COMPUTE_STRANGE_VALUE_1 = new MethodTester(
        STRANGE_THINGS,
        "computeStrangeValue1",
        SIMILARITY,
        PUBLIC | STATIC,
        int.class,
        List.of(
            new ParameterMatcher(int.class)
        )
    );

    public static final MethodTester COMPUTE_STRANGE_VALUE_2 = new MethodTester(
        STRANGE_THINGS,
        "computeStrangeValue2",
        SIMILARITY,
        PUBLIC | STATIC,
        int.class,
        List.of(
            new ParameterMatcher(int.class)
        )
    );

    public static final MethodTester TRANSFORM_ARRAY_ITERATIVELY = new MethodTester(
        STRANGE_THINGS,
        "transformArrayIteratively",
        SIMILARITY,
        PUBLIC | STATIC,
        double[].class,
        List.of(
            new ParameterMatcher(double[].class),
            new ParameterMatcher(TRAVERSER.assureClassResolved().getTheClass())));
    public static final MethodTester TRANSFORM_ARRAY_RECURSIVELY = new MethodTester(
        STRANGE_THINGS,
        "transformArrayRecursively",
        SIMILARITY,
        PUBLIC | STATIC,
        double[].class,
        List.of(
            new ParameterMatcher(double[].class),
            new ParameterMatcher(TRAVERSER.assureClassResolved().getTheClass())));
    public static final MethodTester DO_THE_RECURSION = new MethodTester(
        STRANGE_THINGS,
        "doTheRecursion",
        SIMILARITY,
        PRIVATE | STATIC,
        void.class,
        List.of(
            new ParameterMatcher(double[].class),
            new ParameterMatcher(double[].class),
            new ParameterMatcher(TRAVERSER.getTheClass()),
            new ParameterMatcher(int.class))
    );

    public static final MethodTester EVALUATE = new MethodTester(
        STRANGE_THINGS,
        "evaluate",
        SIMILARITY,
        PUBLIC | STATIC,
        int.class,
        List.of(
            new ParameterMatcher(char[].class))
    );

    public static final MethodTester EVALUATE_RECURSIVELY = new MethodTester(
        STRANGE_THINGS,
        "evaluateRecursively",
        SIMILARITY,
        PRIVATE | STATIC,
        RETURN_DATA.assureClassResolved().getTheClass(),
        List.of(
            new ParameterMatcher(char[].class),
            new ParameterMatcher(int.class))
    );

    public static final MethodTester TEST_EVALUATE = new MethodTester(
        STRANGE_THINGS,
        "testEvaluate",
        SIMILARITY,
        PUBLIC | STATIC,
        boolean.class,
        List.of(
            new ParameterMatcher(char[].class),
            new ParameterMatcher(int.class)
        )
    );
    public static final MockedStatic.Verification TEST_EVALUATE_VERIFICATION =
        () -> testEvaluate_STUDENT(any(char[].class), anyInt());

    // TODO remove
//    public static final MockedStatic<?> STRANGE_THINGS_MOCK = null;//Mockito.mockStatic(
        //STRANGE_THINGS.assureClassResolved().getTheClass(), Mockito.CALLS_REAL_METHODS);

    public static MockedStatic<?> getStrangeThingsMock(Method... methodsToMock) {
        return Utils.MockitoUtils.mockUseDefaultAnswer(STRANGE_THINGS.getTheClass(), methodsToMock);
    }

    public static int whatTheHellIsThat1_STUDENT(int m, int n) {
        return WHAT_THE_HELL_IS_THAT_1.assureMethodResolved().invokeStatic(m, n);
    }

    public static int whatTheHellIsThat1_STUDENT() {
        return whatTheHellIsThat1_STUDENT(anyInt(), anyInt());
    }

    public static int whatTheHellIsThat2_STUDENT(int m, int n) {
        return WHAT_THE_HELL_IS_THAT_2.assureMethodResolved().invokeStatic(m, n);
    }

    public static int whatTheHellIsThat2_STUDENT() {
        return whatTheHellIsThat2_STUDENT(anyInt(), anyInt());
    }

    public static boolean testEvaluate_STUDENT(char[] c, int e) {
        System.out.println(STRANGE_THINGS.is_spy());
        return TEST_EVALUATE.assureMethodResolved().invokeStatic(c, e);
    }

    public static int computeStrangeValue1_STUDENT(int n) {
        return COMPUTE_STRANGE_VALUE_1.assureMethodResolved().invokeStatic(n);
    }

    public static int computeStrangeValue1_STUDENT() {
        return computeStrangeValue1_STUDENT(anyInt());
    }

    public static int computeStrangeValue2_STUDENT(int n) {
        return COMPUTE_STRANGE_VALUE_2.assureMethodResolved().invokeStatic(n);
    }

    public static int computeStrangeValue2_STUDENT() {
        return computeStrangeValue2_STUDENT(anyInt());
    }

    public static double[] transformArrayIteratively_STUDENT(double[] array, Traverser_STUD traverser) {
        return TRANSFORM_ARRAY_ITERATIVELY.assureMethodResolved().invokeStatic(array, traverser);
    }

    public static double[] transformArrayRecursively_STUDENT(double[] array, Traverser_STUD traverser) {
        return TRANSFORM_ARRAY_RECURSIVELY.assureMethodResolved().invokeStatic(array, traverser);
    }

    public static void doTheRecursion_STUD(double[] in, double[] out, Traverser_STUD traverser, int index) {
        DO_THE_RECURSION.assureMethodResolved().invokeStatic(in, out, traverser, index);
    }

    // TODO unpack TraverserMock?!

    public static void doTheRecursion_STUD() {
        doTheRecursion_STUD(any(double[].class), any(double[].class), any(), anyInt());
    }

    public static int evaluate_STUD(char[] c) {
        return EVALUATE.assureMethodResolved().invokeStatic(c);
    }

    public static int evaluate_STUD() {
        return evaluate_STUD(any(char[].class));
    }

    public static ReturnData_STUD evaluateRecursively_STUD(char[] c, int i) {
        return new ReturnData_STUD(EVALUATE_RECURSIVELY.assureMethodResolved().invokeStatic(c, i));
    }

    public static ReturnData_STUD evaluateRecursively_STUD() {
        return evaluateRecursively_STUD(any(char[].class), anyInt());
    }

}
