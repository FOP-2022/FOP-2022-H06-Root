package student;

import reflection.ClassTester;
import reflection.MethodTester;
import reflection.ParameterMatcher;

import java.lang.reflect.Modifier;
import java.util.List;

import static h.Global.SIMILARITY;
import static java.lang.reflect.Modifier.PUBLIC;
import static java.util.Objects.requireNonNullElseGet;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

public class UpTraverser_STUD extends Traverser_STUD {

    private static ClassTester<?> cUpTraverserO;

    public static ClassTester<?> cUpTraverser() {
        return cUpTraverserO = requireNonNullElseGet(cUpTraverserO, () -> new ClassTester<>(
            "h06",
            "UpTraverser",
            SIMILARITY,
            Modifier.PUBLIC).assureResolved());
    }

    private static MethodTester mGetFirstIndexO;

    public static MethodTester mGetFirstIndexS() {
        return mGetFirstIndexO = requireNonNullElseGet(mGetFirstIndexO, () -> new MethodTester(
            cUpTraverser(),
            "getFirstIndex",
            SIMILARITY,
            PUBLIC,
            int.class,
            List.of(new ParameterMatcher(double[].class))
        ).assureResolved());
    }

    private static MethodTester mGetNextIndexO;

    public static MethodTester mGetNextIndexS() {
        return mGetNextIndexO = requireNonNullElseGet(mGetNextIndexO, () -> new MethodTester(
            cUpTraverser(),
            "getNextIndex",
            SIMILARITY,
            PUBLIC,
            int.class,
            List.of(new ParameterMatcher(int.class))
        ).assureResolved());
    }

    private final Object object;

    public UpTraverser_STUD() {
        object = mock(cUpTraverser().getTheClass(), CALLS_REAL_METHODS);
        cUpTraverser().setClassInstance(object);
    }

    @Override
    public int getFirstIndex(double[] array) {
        return mGetFirstIndexS().invoke(object, (Object) array);
    }

    @Override
    public int getNextIndex(int index) {
        return mGetNextIndexS().invoke(object, index);
    }

    @Override
    public Object getActualObject() {
        return object;
    }
}
