package student;

import reflection.ClassTester;
import reflection.MethodTester;
import reflection.ParameterMatcher;

import java.lang.reflect.Modifier;
import java.util.List;

import static java.lang.reflect.Modifier.PUBLIC;
import static h.Global.SIMILARITY;

public class DownTraverser_STUD implements Traverser_STUD {

    public static final ClassTester<?> DOWN_TRAVERSER = new ClassTester(
        "h06",
        "DownTraverser",
        SIMILARITY,
        Modifier.PUBLIC);

    public static final MethodTester DOWN_TRAVERSER_GET_FIRST_INDEX = new MethodTester(
        DOWN_TRAVERSER,
        "getFirstIndex",
        SIMILARITY,
        PUBLIC,
        int.class,
        List.of(new ParameterMatcher(double[].class))
    );

    public static final MethodTester DOWN_TRAVERSER_GET_NEXT_INDEX = new MethodTester(
        DOWN_TRAVERSER,
        "getNextIndex",
        SIMILARITY,
        PUBLIC,
        int.class,
        List.of(new ParameterMatcher(int.class))
    );

    private final Object object;

    public DownTraverser_STUD() {
        object = DOWN_TRAVERSER.assureClassResolved().getNewInstance();
    }

    @Override
    public int getFirstIndex(double[] array) {
        return DOWN_TRAVERSER_GET_FIRST_INDEX.assureMethodResolved().invoke(object, array);
    }

    @Override
    public int getNextIndex(int index) {
        return DOWN_TRAVERSER_GET_NEXT_INDEX.assureMethodResolved().invoke(object, index);
    }

    @Override
    public Object getActualObject() {
        return object;
    }
}
