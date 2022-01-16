package student;

import reflection.ClassTester;
import reflection.MethodTester;
import reflection.ParameterMatcher;

import java.lang.reflect.Modifier;
import java.util.List;

import static h.Global.SIMILARITY;
import static java.lang.reflect.Modifier.PUBLIC;

public class UpTraverser_STUD implements Traverser_STUD {

    public static final ClassTester<?> UP_TRAVERSER = new ClassTester(
        "h06",
        "UpTraverser",
        SIMILARITY,
        Modifier.PUBLIC);

    public static final MethodTester UP_TRAVERSER_GET_FIRST_INDEX = new MethodTester(
        UP_TRAVERSER,
        "getFirstIndex",
        SIMILARITY,
        PUBLIC,
        int.class,
        List.of(new ParameterMatcher(double[].class))
    );

    public static final MethodTester UP_TRAVERSER_GET_NEXT_INDEX = new MethodTester(
        UP_TRAVERSER,
        "getNextIndex",
        SIMILARITY,
        PUBLIC,
        int.class,
        List.of(new ParameterMatcher(int.class))
    );

    private final Object object;

    public UpTraverser_STUD() {
        object = UP_TRAVERSER.assureClassResolved().getNewInstance();
    }

    @Override
    public int getFirstIndex(double[] array) {
        return UP_TRAVERSER_GET_FIRST_INDEX.assureMethodResolved().invoke(object, array);
    }

    @Override
    public int getNextIndex(int index) {
        return UP_TRAVERSER_GET_NEXT_INDEX.assureMethodResolved().invoke(object, index);
    }


    @Override
    public Object getActualObject() {
        return object;
    }
}
