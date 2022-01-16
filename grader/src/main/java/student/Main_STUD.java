package student;

import reflection.ClassTester;
import reflection.MethodTester;

import java.util.Collections;

import static h.Global.SIMILARITY;

public class Main_STUD {

    public static final ClassTester<?> MAIN = new ClassTester<>(
        "h06",
        "Main",
        SIMILARITY
        );

    public static final MethodTester METHOD_MAIN = new MethodTester(
        MAIN.assureClassResolved(),
        "main",
        SIMILARITY,
        0,
        void.class,
        Collections.emptyList()
    );

    public static void mainMock() {
        METHOD_MAIN.assureMethodResolved().invokeStatic();
    }


}
