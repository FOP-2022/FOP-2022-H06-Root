package h;

import org.junit.jupiter.api.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtIf;
import student.StrangeThings_STUD;
import tutor.Utils;
import tutor.Utils.State;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static reference.StrangeThings_REF.computeStrangeValueRef;
import static reference.StrangeThings_REF.whatTheHellIsThatRef;
import static h.Global.RANDOM;
import static h.Global.T;
import static student.StrangeThings_STUD.*;
import static tutor.Utils.Messages.*;
import static tutor.Utils.TestCollection.Mode.SHOW_ALL;
import static tutor.Utils.TestCollection.Mode.SHOW_FIRST;
import static tutor.Utils.TestCollection.test;

@TestForSubmission("h06")
@DisplayName("H2")
public class H2_Tests {

    @TestForSubmission("h06")
    @Nested
    @DisplayName("1 | Method <computeStrangeValue1>")
    public static class H2_1_1 {

        @BeforeAll
        static void beforeAll() {
            test()
                .add(STRANGE_THINGS::assureClassResolved)
                .terminateOnException()
                .add(COMPUTE_STRANGE_VALUE_1::assureMethodResolved)
                .run(SHOW_ALL);
        }

        @BeforeEach
        void beforeEach() {
            RANDOM.setSeed(42);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            COMPUTE_STRANGE_VALUE_1.assertCorrectDeclaration();
        }

        @Test
        @DisplayName("2 | Recursion")
        public void test2() {
            COMPUTE_STRANGE_VALUE_1.assertDirectlyRecursive();
        }

        @Test
        @DisplayName("3 | Required Constructs")
        public void test3() {
            COMPUTE_STRANGE_VALUE_1.assertConstructsUsed(List.of(
                CtConditional.class
            ));
        }

        @Test
        @DisplayName("4 | Disallowed Constructs")
        public void test4() {
            COMPUTE_STRANGE_VALUE_1.assertConstructsNotUsed(List.of(
                CtIf.class
            ));
        }

        @Test
        @DisplayName("5 | Anchor (N<=0)")
        public void test5() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue1_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return fail(unexpectedRecursiveCallFor("computeStrangeValue1", Arrays.asList(a.getArguments())));
                });
                var test = test("computeStrangeValue1");
                rangeClosed(-T + 1, 0).forEach(i -> test.add(() -> {
                    numberOfCall.set(0);
                    if (numberOfCall.getInt() > 1)
                        fail(unexpectedRecursiveCallFor("computeStrangeValue1", List.of(i)));
                }, i));
                test.run(SHOW_FIRST);
            }
        }

        @Test
        @DisplayName("6 | Recursion I (N%2=0)")
        public void test6() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue1_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return computeStrangeValueRef(a.getArgument(0));
                });
                var test = test("computeStrangeValue1");
                range(2, 2 + T)
                    .map(n -> 2 * n)
                    .forEach(n -> test.add(() -> {
                        numberOfCall.set(0);
                        computeStrangeValue1_STUDENT(n);
                        if (numberOfCall.getInt() == 1)
                            fail(wasNotCalledRecursively("computeStrangeValue1"));
                    }));
                test.run(SHOW_FIRST);
            }
        }

        @Test
        @DisplayName("7 | Recursion II (N%2!=0)")
        public void test7() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue1_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return computeStrangeValueRef(a.getArgument(0));
                });
                var test = test("computeStrangeValue1");
                range(2, 2 + T)
                    .map(n -> 2 * n - 1)
                    .forEach(n -> test.add(() -> {
                        numberOfCall.set(0);
                        computeStrangeValue1_STUDENT(n);
                        if (numberOfCall.getInt() == 1)
                            fail(wasNotCalledRecursively("computeStrangeValue1"));
                    }));
                test.run(SHOW_FIRST);
            }
        }

        @Test
        @DisplayName("8 | Return Values")
        public void test8() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue1_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return computeStrangeValueRef(a.getArgument(0));
                });
                var test = test("computeStrangeValue1");
                rangeClosed(-T + 1, 0).forEach(i -> test.add(() -> {
                    numberOfCall.set(0);
                    assertEquals(1, computeStrangeValue1_STUDENT(i));
                    if (numberOfCall.getInt() > 1)
                        fail(unexpectedRecursiveCallFor("computeStrangeValue1", List.of(i)));
                }, i));
                test.run(SHOW_FIRST);
            }
        }

    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("2 | Method <computeStrangeValue2>")
    public static class H2_1_2 {

        @BeforeAll
        static void beforeAll() {
            test()
                .add(STRANGE_THINGS::assureClassResolved)
                .terminateOnException()
                .add(COMPUTE_STRANGE_VALUE_2::assureMethodResolved)
                .run(SHOW_ALL);
        }

        @BeforeEach
        void beforeEach() {
            RANDOM.setSeed(42);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            COMPUTE_STRANGE_VALUE_2.assertCorrectDeclaration();
        }

        @Test
        @DisplayName("2 | Recursion")
        public void test2() {
            COMPUTE_STRANGE_VALUE_2.assertDirectlyRecursive();
        }

        @Test
        @DisplayName("3 | Required Constructs")
        public void test3() {
            COMPUTE_STRANGE_VALUE_2.assertConstructsUsed(List.of(
                CtIf.class
            ));
        }

        @Test
        @DisplayName("4 | Disallowed Constructs")
        public void test4() {
            COMPUTE_STRANGE_VALUE_2.assertConstructsNotUsed(List.of(
                CtConditional.class
            ));
        }

        @Test
        @DisplayName("5 | Anchor (N<=0)")
        public void test5() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue2_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return fail(unexpectedRecursiveCallFor("computeStrangeValue2", Arrays.asList(a.getArguments())));
                });
                var test = test("computeStrangeValue2");
                rangeClosed(-T + 1, 0).forEach(i -> test.add(() -> {
                    numberOfCall.set(0);
                    if (numberOfCall.getInt() > 1)
                        fail(unexpectedRecursiveCallFor("computeStrangeValue2", List.of(i)));
                }, i));
                test.run(SHOW_FIRST);
            }
        }

        @Test
        @DisplayName("6 | Recursion I (N%2=0)")
        public void test6() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue2_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return computeStrangeValueRef(a.getArgument(0));
                });
                var test = test("computeStrangeValue2");
                range(2, 2 + T)
                    .map(n -> 2 * n)
                    .forEach(n -> test.add(() -> {
                        numberOfCall.set(0);
                        computeStrangeValue2_STUDENT(n);
                        if (numberOfCall.getInt() == 1)
                            fail(wasNotCalledRecursively("computeStrangeValue2"));
                    }));
                test.run(SHOW_FIRST);
            }
        }

        @Test
        @DisplayName("7 | Recursion II (N%2!=0)")
        public void test7() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue2_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return computeStrangeValueRef(a.getArgument(0));
                });
                var test = test("computeStrangeValue2");
                range(2, 2 + T)
                    .map(n -> 2 * n - 1)
                    .forEach(n -> test.add(() -> {
                        numberOfCall.set(0);
                        computeStrangeValue2_STUDENT(n);
                        if (numberOfCall.getInt() == 1)
                            fail(wasNotCalledRecursively("computeStrangeValue2"));
                    }));
                test.run(SHOW_FIRST);
            }
        }

        @Test
        @DisplayName("8 | Return Values")
        public void test8() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::computeStrangeValue2_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return computeStrangeValueRef(a.getArgument(0));
                });
                var test = test("computeStrangeValue2");
                rangeClosed(-T + 1, 0).forEach(i -> test.add(() -> {
                    numberOfCall.set(0);
                    assertEquals(1, computeStrangeValue2_STUDENT(i));
                    if (numberOfCall.getInt() > 1)
                        fail(unexpectedRecursiveCallFor("computeStrangeValue2", List.of(i)));
                }, i));
                test.run(SHOW_FIRST);
            }
        }

    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("3 | Method <whatTheHellIsThat1>")
    public static class H2_2_1 {

        @BeforeAll
        static void beforeAll() {
            test()
                .add(STRANGE_THINGS::assureClassResolved)
                .terminateOnException()
                .add(WHAT_THE_HELL_IS_THAT_1::assureMethodResolved)
                .run(SHOW_ALL);
        }

        @BeforeEach
        void beforeEach() {
            RANDOM.setSeed(42);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            WHAT_THE_HELL_IS_THAT_1.assertCorrectDeclaration();
        }

        @Test
        @DisplayName("2 | Recursion")
        public void test2() {
            WHAT_THE_HELL_IS_THAT_1.assertDirectlyRecursive();
        }

        @Test
        @DisplayName("3 | Required Constructs")
        public void test3() {
            WHAT_THE_HELL_IS_THAT_1.assertConstructsUsed(List.of(
                CtConditional.class
            ));
        }

        @Test
        @DisplayName("4 | Disallowed Constructs")
        public void test4() {
            WHAT_THE_HELL_IS_THAT_1.assertConstructsNotUsed(List.of(
                CtIf.class
            ));
        }

        @Test
        @DisplayName("5 | Anchor (if m<=0)")
        public void test5() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat1_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return fail(unexpectedRecursiveCallFor("whatTheHellIsThat1", asList(a.getArguments())));
                });
                var test = test("whatTheHellIsThat1");
                rangeClosed(-T + 1, 0).forEach(m -> {
                    numberOfCall.set(0);
                    test.run(() -> assertEquals(0, whatTheHellIsThat1_STUDENT(m, 0)), m, 0);
                    var n = RANDOM.nextInt();
                    numberOfCall.set(0);
                    test.run(() -> assertEquals(0, whatTheHellIsThat1_STUDENT(m, n)), m, n);
                });
            }
        }

        @Test
        @DisplayName("6 | Anchor (if n<=0)")
        public void test6() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat1_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return fail(unexpectedRecursiveCallFor("whatTheHellIsThat1", asList(a.getArguments())));
                });
                rangeClosed(-T + 1, 0).forEach(n -> {
                    numberOfCall.set(0);
                    whatTheHellIsThat1_STUDENT(0, n);
                    var m = RANDOM.nextInt();
                    numberOfCall.set(0);
                    whatTheHellIsThat1_STUDENT(m, n);
                });
            }
        }

        @Test
        @DisplayName("7 | Recursion (if m<n)")
        public void test7() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                var firstM = new State();
                var firstN = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat1_STUDENT).then(a -> {
                    int m = a.getArgument(0);
                    int n = a.getArgument(1);
                    if (numberOfCall.incInt() == 0) {
                        firstM.set(m);
                        firstN.set(n);
                        return a.callRealMethod();
                    }
                    test("whatTheHellIsThat1")
                        .add(() -> assertEquals(1 + firstM.getInt(), m, Utils.Messages.incorrectParameter("m")), m, n)
                        .add(() -> assertEquals(firstN.getInt() - firstM.getInt(), n,
                            Utils.Messages.incorrectParameter("n")), m, n)
                        .run();
                    return whatTheHellIsThatRef(m, n);
                });
                range(1, 10).forEach(m -> rangeClosed(m + 1, 10).forEach(n -> {
                    if (m == 1 && n == 2 || m == 2 & n == 1)
                        return;
                    numberOfCall.set(0);
                    whatTheHellIsThat1_STUDENT(m, n);
                    if (numberOfCall.getInt() == 1)
                        fail(wasNotCalledRecursively("whatTheHellIsThat1"));
                }));
            }
        }

        @Test
        @DisplayName("8 | Recursion (else)")
        public void test8() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                var firstM = new State();
                var firstN = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat1_STUDENT).then(a -> {
                    int m = a.getArgument(0);
                    int n = a.getArgument(1);
                    if (numberOfCall.incInt() == 0) {
                        firstM.set(m);
                        firstN.set(n);
                        return a.callRealMethod();
                    }
                    test("whatTheHellIsThat1")
                        .add(() -> assertEquals(1 + firstN.getInt(), m, Utils.Messages.incorrectParameter("m")), m, n)
                        .add(() -> assertEquals(firstM.getInt() - firstN.getInt(), n,
                            Utils.Messages.incorrectParameter("n")), m, n)
                        .run();
                    return whatTheHellIsThatRef(m, n);
                });
                range(1, 10).forEach(n -> rangeClosed(n, 10).forEach(m -> {
                    if (m == 1 && n == 2 || m == 2 & n == 1)
                        return;
                    numberOfCall.set(0);
                    whatTheHellIsThat1_STUDENT(m, n);
                    if (numberOfCall.getInt() == 1)
                        fail(wasNotCalledRecursively("whatTheHellIsThat1"));
                }));
            }
        }

        @Test
        @DisplayName("9 | Return Values")
        public void test9() {
            try (var ignored = StrangeThings_STUD.getStrangeThingsMock()) {
                var test = test("whatTheHellIsThat1");
                range(-10, 10).forEach(n -> rangeClosed(-10, 10).forEach(m -> {
                    if (m == 1 && n == 2 || m == 2 & n == 1)
                        return;
                    test.add(() -> assertEquals(whatTheHellIsThatRef(m, n), whatTheHellIsThat1_STUDENT(m, n),
                        incorrectReturnValue()), m, n);
                    if (m == 0 && n == 0)
                        test.letShow();
                }));
                test.run(SHOW_FIRST);
            }
        }
    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("3 | Method <whatTheHellIsThat2>")
    public static class H2_2_2 {

        @BeforeAll
        static void beforeAll() {
            test()
                .add(STRANGE_THINGS::assureClassResolved)
                .terminateOnException()
                .add(WHAT_THE_HELL_IS_THAT_2::assureMethodResolved)
                .run(SHOW_ALL);
        }

        @BeforeEach
        void beforeEach() {
            RANDOM.setSeed(42);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            WHAT_THE_HELL_IS_THAT_2.assertCorrectDeclaration();
        }

        @Test
        @DisplayName("2 | Recursion")
        public void test2() {
            WHAT_THE_HELL_IS_THAT_2.assertDirectlyRecursive();
        }

        @Test
        @DisplayName("3 | Required Constructs")
        public void test3() {
            WHAT_THE_HELL_IS_THAT_2.assertConstructsUsed(List.of(
                CtIf.class
            ));
        }

        @Test
        @DisplayName("4 | Disallowed Constructs")
        public void test4() {
            WHAT_THE_HELL_IS_THAT_2.assertConstructsNotUsed(List.of(
                CtConditional.class
            ));
        }

        @Test
        @DisplayName("5 | Anchor (if m<=0)")
        public void test5() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat2_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return fail(unexpectedRecursiveCallFor("whatTheHellIsThat2", asList(a.getArguments())));
                });
                var test = test("whatTheHellIsThat2");
                rangeClosed(-T + 1, 0).forEach(m -> {
                    numberOfCall.set(0);
                    test.run(() -> assertEquals(0, StrangeThings_STUD.whatTheHellIsThat2_STUDENT(m, 0)), m, 0);
                    var n = RANDOM.nextInt();
                    numberOfCall.set(0);
                    test.run(() -> assertEquals(0, StrangeThings_STUD.whatTheHellIsThat2_STUDENT(m, n)), m, n);
                });
            }
        }

        @Test
        @DisplayName("6 | Anchor (if n<=0)")
        public void test6() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat2_STUDENT).then(a -> {
                    if (numberOfCall.incInt() == 0)
                        return a.callRealMethod();
                    return fail(unexpectedRecursiveCallFor("whatTheHellIsThat2", asList(a.getArguments())));
                });
                rangeClosed(-T + 1, 0).forEach(n -> {
                    numberOfCall.set(0);
                    StrangeThings_STUD.whatTheHellIsThat2_STUDENT(0, n);
                    var m = RANDOM.nextInt();
                    numberOfCall.set(0);
                    StrangeThings_STUD.whatTheHellIsThat2_STUDENT(m, n);
                });
            }
        }

        @Test
        @DisplayName("7 | Recursion (if m<n)")
        public void test7() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                var firstM = new State();
                var firstN = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat2_STUDENT).then(a -> {
                    int m = a.getArgument(0);
                    int n = a.getArgument(1);
                    if (numberOfCall.incInt() == 0) {
                        firstM.set(m);
                        firstN.set(n);
                        return a.callRealMethod();
                    }
                    test("whatTheHellIsThat2")
                        .add(() -> assertEquals(1 + firstM.getInt(), m, Utils.Messages.incorrectParameter("m")), m, n)
                        .add(() -> assertEquals(firstN.getInt() - firstM.getInt(), n,
                            Utils.Messages.incorrectParameter("n")), m, n)
                        .run();
                    return whatTheHellIsThatRef(m, n);
                });
                range(1, 10).forEach(m -> rangeClosed(m + 1, 10).forEach(n -> {
                    if (m == 1 && n == 2 || m == 2 & n == 1)
                        return;
                    numberOfCall.set(0);
                    StrangeThings_STUD.whatTheHellIsThat2_STUDENT(m, n);
                    if (numberOfCall.getInt() == 1)
                        fail(wasNotCalledRecursively("whatTheHellIsThat2"));
                }));
            }
        }

        @Test
        @DisplayName("8 | Recursion (else)")
        public void test8() {
            try (var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock()) {
                var numberOfCall = new State();
                var firstM = new State();
                var firstN = new State();
                STRANGE_THINGS_MOCK.when(StrangeThings_STUD::whatTheHellIsThat2_STUDENT).then(a -> {
                    int m = a.getArgument(0);
                    int n = a.getArgument(1);
                    if (numberOfCall.incInt() == 0) {
                        firstM.set(m);
                        firstN.set(n);
                        return a.callRealMethod();
                    }
                    test("whatTheHellIsThat2")
                        .add(() -> assertEquals(1 + firstN.getInt(), m, Utils.Messages.incorrectParameter("m")), m, n)
                        .add(() -> assertEquals(firstM.getInt() - firstN.getInt(), n,
                            Utils.Messages.incorrectParameter("n")), m, n)
                        .run();
                    return whatTheHellIsThatRef(m, n);
                });
                range(1, 10).forEach(n -> rangeClosed(n, 10).forEach(m -> {
                    if (m == 1 && n == 2 || m == 2 & n == 1)
                        return;
                    numberOfCall.set(0);
                    StrangeThings_STUD.whatTheHellIsThat2_STUDENT(m, n);
                    if (numberOfCall.getInt() == 1)
                        fail(wasNotCalledRecursively("whatTheHellIsThat2"));
                }));
            }
        }

        @Test
        @DisplayName("9 | Return Values")
        public void test9() {
            try (var ignored = StrangeThings_STUD.getStrangeThingsMock()) {
                var test = test("whatTheHellIsThat2");
                range(-10, 10).forEach(n -> rangeClosed(-10, 10).forEach(m -> {
                    if (m == 1 && n == 2 || m == 2 & n == 1)
                        return;
                    test.add(() -> assertEquals(whatTheHellIsThatRef(m, n),
                        StrangeThings_STUD.whatTheHellIsThat2_STUDENT(m, n), incorrectReturnValue()), m, n);
                    if (m == 0 && n == 0)
                        test.letShow();
                }));
                test.run(SHOW_FIRST);
            }
        }

    }


}
