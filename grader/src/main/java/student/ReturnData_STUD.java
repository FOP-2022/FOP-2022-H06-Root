package student;

import org.jetbrains.annotations.NotNull;
import reflection.AttributeMatcher;
import reflection.AttributeTester;
import reflection.ClassTester;
import tutor.Mocked;

import java.lang.reflect.Modifier;

import static h.Global.SIMILARITY;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ReturnData_STUD implements Mocked {

    public static final ClassTester<?> RETURN_DATA = new ClassTester(
        "h06",
        "ReturnData",
        SIMILARITY);

    public static final AttributeTester RESULT = new AttributeTester()
        .setClassTester(RETURN_DATA)
        .setMatcher(
            new AttributeMatcher(
                "result",
                SIMILARITY,
                Modifier.PUBLIC,
                int.class));

    public static final AttributeTester NEXT_INDEX = new AttributeTester()
        .setClassTester(RETURN_DATA)
        .setMatcher(
            new AttributeMatcher(
        "nextIndex",
        SIMILARITY,
        Modifier.PUBLIC,
        int.class)
    );

    private final Object object;

    public ReturnData_STUD(Object object) {
        assertSame(RETURN_DATA.getTheClass(), object.getClass(), "INTERNAL ERROR");
        this.object = requireNonNull(object);
    }

    public ReturnData_STUD() {
        this(RETURN_DATA.assureClassResolved().getNewInstance());
    }

    public ReturnData_STUD(int result, int nextIndex) {
        this(RETURN_DATA.assureClassResolved().getNewInstance());
        setResult(result);
        setNextIndex(nextIndex);
    }

    public void setResult(int result) {
        RESULT.setValue(object, result);
    }

    public void setNextIndex(int result) {
        NEXT_INDEX.setValue(object, result);
    }

    public int getResult() {
        return RESULT.getValue(object);
    }

    public int getNextIndex() {
        return NEXT_INDEX.getValue(object);
    }

    public @NotNull Object getActualObject() {
        return object;
    }


}
