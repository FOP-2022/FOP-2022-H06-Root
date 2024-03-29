package student;

import reflection.ClassTester;
import reflection.MethodTester;

import java.util.Collections;
import java.util.Objects;

import static h.Global.SIMILARITY;

public class Main_STUD {

    private static ClassTester<?> cMainO;

    public static ClassTester<?> cMain() {
        return cMainO = Objects.requireNonNullElseGet(cMainO, () -> new ClassTester<>(
            "h06",
            "Main",
            SIMILARITY
        ).assureResolved());
    }

    private static MethodTester mMainO;

    public static MethodTester mMain() {
        return mMainO = Objects.requireNonNullElseGet(mMainO, () -> new MethodTester(
            cMain(),
            "main",
            SIMILARITY,
            0,
            void.class,
            Collections.emptyList()
        ).assureResolved());
    }

    public static void main_STUD() {
        mMain().invokeStatic((Object) new String[0]);
    }
}
