package student;


import org.eclipse.jdt.core.dom.Modifier;
import reflection.ClassTester;
import reflection.IdentifierMatcher;
import reflection.MethodTester;
import reflection.ParameterMatcher;
import tutor.Mocked;

import java.util.List;

import static java.lang.reflect.Modifier.*;
import static h.Global.SIMILARITY;

public interface Traverser_STUD extends Mocked {

    public static final ClassTester<?> TRAVERSER = new ClassTester<>(
        "h06",
        "Traverser",
        SIMILARITY,
        PUBLIC | ABSTRACT | INTERFACE);
    public static final MethodTester TRAVERSER_GET_NEXT_INDEX = new MethodTester(
        TRAVERSER,
        "getNextIndex",
        SIMILARITY,
        PUBLIC,
        int.class,
        List.of(new ParameterMatcher(int.class))
    );
    public static final MethodTester TRAVERSER_GET_FIRST_INDEX = new MethodTester(
        TRAVERSER,
        "getFirstIndex",
        SIMILARITY,
        Modifier.NONE,
        int.class,
        List.of(new ParameterMatcher(double[].class))
    );
    public static final IdentifierMatcher TRAVERSER_MATCHER = new IdentifierMatcher(
        "Traverser",
        "h06",
        SIMILARITY
    );
    public static final ClassTester<?> UP_TRAVERSER_CLASS = new ClassTester<>(
        "h06",
        "UpTraverser",
        SIMILARITY,
        PUBLIC,
        List.of(TRAVERSER_MATCHER)
    );

    public int getFirstIndex(double[] array);

    public int getNextIndex(int index);

}
