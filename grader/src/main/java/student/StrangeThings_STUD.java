package student;

import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import reflection.ClassTester;
import reflection.MethodTester;
import reflection.ParameterMatcher;

import java.util.List;

import static h.Global.SIMILARITY;
import static java.lang.reflect.Modifier.PRIVATE;
import static java.lang.reflect.Modifier.PUBLIC;
import static java.lang.reflect.Modifier.STATIC;
import static java.util.Objects.requireNonNullElseGet;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static student.ReturnData_STUD.cReturnData;
import static student.Traverser_STUD.cTraverser;

public class StrangeThings_STUD {

    private static ClassTester<?> cStrangeThings;

    public static ClassTester<?> cStrangeThings() {
        return cStrangeThings = requireNonNullElseGet(cStrangeThings, () -> new ClassTester<>(
            "h06",
            "StrangeThings",
            SIMILARITY).assureResolved());
    }

    private static MethodTester mWhatTheHellIsThat1;

    public static MethodTester mWhatTheHellIsThat1() {
        return mWhatTheHellIsThat1 = requireNonNullElseGet(mWhatTheHellIsThat1, () -> new MethodTester(
            cStrangeThings(),
            "whatTheHellIsThat1",
            SIMILARITY,
            PUBLIC | STATIC,
            int.class,
            List.of(
                new ParameterMatcher(int.class),
                new ParameterMatcher(int.class)
            )
        ).assureResolved());
    }

    private static MethodTester mWhatTheHellIsThat2;

    public static MethodTester mWhatTheHellIsThat2() {
        return mWhatTheHellIsThat2 = requireNonNullElseGet(mWhatTheHellIsThat2, () -> new MethodTester(
            cStrangeThings(),
            "whatTheHellIsThat2",
            SIMILARITY,
            PUBLIC | STATIC,
            int.class,
            List.of(
                new ParameterMatcher(int.class),
                new ParameterMatcher(int.class)
            )
        ).assureResolved());
    }

    private static MethodTester mComputeStrangeValue1;

    public static MethodTester mComputeStrangeValue1() {
        return mComputeStrangeValue1 = requireNonNullElseGet(mComputeStrangeValue1, () -> new MethodTester(
            cStrangeThings(),
            "computeStrangeValue1",
            SIMILARITY,
            PUBLIC | STATIC,
            int.class,
            List.of(
                new ParameterMatcher(int.class)
            )
        ).assureResolved());
    }

    private static MethodTester mComputeStrangeValue2;

    public static MethodTester mComputeStrangeValue2() {
        return mComputeStrangeValue2 = requireNonNullElseGet(mComputeStrangeValue2, () -> new MethodTester(
            cStrangeThings(),
            "computeStrangeValue2",
            SIMILARITY,
            PUBLIC | STATIC,
            int.class,
            List.of(
                new ParameterMatcher(int.class)
            )
        ).assureResolved());
    }

    private static MethodTester mTransformArrayIteratively;

    public static MethodTester mTransformArrayIteratively() {
        return mTransformArrayIteratively = requireNonNullElseGet(mTransformArrayIteratively, () -> new MethodTester(
            cStrangeThings(),
            "transformArrayIteratively",
            SIMILARITY,
            PUBLIC | STATIC,
            double[].class,
            List.of(
                new ParameterMatcher(double[].class),
                new ParameterMatcher(cTraverser().getTheClass()))).assureResolved());
    }

    private static MethodTester mTransformArrayRecursively;

    public static MethodTester mTransformArrayRecursively() {
        return mTransformArrayRecursively = requireNonNullElseGet(mTransformArrayRecursively, () -> new MethodTester(
            cStrangeThings(),
            "transformArrayRecursively",
            SIMILARITY,
            PUBLIC | STATIC,
            double[].class,
            List.of(
                new ParameterMatcher(double[].class),
                new ParameterMatcher(cTraverser().getTheClass()))).assureResolved());
    }

    private static MethodTester mDoTheRecursion;

    public static MethodTester mDoTheRecursion() {
        return mDoTheRecursion = requireNonNullElseGet(mDoTheRecursion, () -> new MethodTester(
            cStrangeThings(),
            "doTheRecursion",
            SIMILARITY,
            PRIVATE | STATIC,
            void.class,
            List.of(
                new ParameterMatcher(double[].class),
                new ParameterMatcher(double[].class),
                new ParameterMatcher(cTraverser().getTheClass()),
                new ParameterMatcher(int.class))
        ).assureResolved());
    }

    private static MethodTester mEvaluate;

    public static MethodTester mEvaluate() {
        return mEvaluate = requireNonNullElseGet(mEvaluate, () -> new MethodTester(
            cStrangeThings(),
            "evaluate",
            SIMILARITY,
            PUBLIC | STATIC,
            int.class,
            List.of(
                new ParameterMatcher(char[].class))
        ).assureResolved());
    }

    private static MethodTester mEvaluateRecursively;

    public static MethodTester getEvaluateRecursively() {
        return mEvaluateRecursively = requireNonNullElseGet(mEvaluateRecursively, () -> new MethodTester(
            cStrangeThings(),
            "evaluateRecursively",
            SIMILARITY,
            PRIVATE | STATIC,
            cReturnData().getTheClass(),
            List.of(
                new ParameterMatcher(char[].class),
                new ParameterMatcher(int.class))
        ).assureResolved());
    }

    private static MethodTester mTestEvaluate;

    public static MethodTester getTestEvaluate() {
        return mTestEvaluate = requireNonNullElseGet(mTestEvaluate, () -> new MethodTester(
            cStrangeThings(),
            "testEvaluate",
            SIMILARITY,
            PUBLIC | STATIC,
            boolean.class,
            List.of(
                new ParameterMatcher(char[].class),
                new ParameterMatcher(int.class)
            )
        ).assureResolved());
    }

    private static Object mock;

    public static Object getStrangeThingsMock() {
        return mock = requireNonNullElseGet(mock, () -> {
            Object m = Mockito.mock(cStrangeThings().getTheClass(), Answers.CALLS_REAL_METHODS);
            cStrangeThings().setClassInstance(m);
            return m;
        });
    }

    public static void resetStrangeThingsMock() {
        Mockito.reset(getStrangeThingsMock());
        getStrangeThingsMock();
    }

    public static int whatTheHellIsThat1_STUD(int m, int n) {
        return mWhatTheHellIsThat1().invoke(getStrangeThingsMock(), m, n);
    }

    public static int whatTheHellIsThat2_STUD(int m, int n) {
        return mWhatTheHellIsThat2().invoke(getStrangeThingsMock(), m, n);
    }

    public static OngoingStubbing<?> whatTheHellIsThat1_STUD() {
        return Mockito.when(mWhatTheHellIsThat1().invoke(getStrangeThingsMock(), anyInt(), anyInt()));
    }

    public static OngoingStubbing<?> whatTheHellIsThat2_STUD() {
        return Mockito.when(mWhatTheHellIsThat2().invoke(getStrangeThingsMock(), anyInt(), anyInt()));
    }

    public static boolean testEvaluate_STUDENT(char[] c, int e) {
        return getTestEvaluate().invoke(getStrangeThingsMock(), c, e);
    }

    public static int computeStrangeValue1_STUD(int n) {
        return mComputeStrangeValue1().invoke(getStrangeThingsMock(), n);
    }

    public static OngoingStubbing<?> computeStrangeValue1_STUD() {
        return Mockito.when(mComputeStrangeValue1().invoke(getStrangeThingsMock(), anyInt()));
    }

    public static int computeStrangeValue2_STUD(int n) {
        return mComputeStrangeValue2().invoke(getStrangeThingsMock(), n);
    }

    public static OngoingStubbing<?> computeStrangeValue2_STUD() {
        return Mockito.when(mComputeStrangeValue2().invoke(getStrangeThingsMock(), anyInt()));
    }

    public static double[] transformArrayIteratively_STUDENT(double[] array, Traverser_STUD traverser) {
        return mTransformArrayIteratively().invoke(getStrangeThingsMock(), array, traverser);
    }

    public static double[] transformArrayRecursively_STUDENT(double[] array, Traverser_STUD traverser) {
        return mTransformArrayRecursively().invoke(getStrangeThingsMock(), array, traverser);
    }

    public static void doTheRecursion_STUD(double[] in, double[] out, Traverser_STUD traverser, int index) {
        mDoTheRecursion().invoke(getStrangeThingsMock(), in, out, traverser, index);
    }

    public static OngoingStubbing<?> doTheRecursion_STUD() {
        return Mockito.when(mDoTheRecursion().invoke(getStrangeThingsMock(), any(double[].class), any(double[].class), any(), anyInt()));
    }

    public static int evaluate_STUD(char[] c) {
        return mEvaluate().invoke(getStrangeThingsMock(), (Object) c);
    }

    public static OngoingStubbing<?> evaluate_STUD() {
        return Mockito.when(mEvaluate().invoke(getStrangeThingsMock(), (Object) any(char[].class)));
    }

    public static ReturnData_STUD evaluateRecursively_STUD(char[] c, int i) {
        return new ReturnData_STUD(getEvaluateRecursively().invoke(getStrangeThingsMock(), c, i));
    }

    public static OngoingStubbing<?> evaluateRecursively_STUD() {
        return Mockito.when(getEvaluateRecursively().invoke(getStrangeThingsMock(), any(char[].class), anyInt()));
    }

}
