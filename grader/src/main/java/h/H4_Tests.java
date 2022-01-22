package h;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import reference.StrangeThings_REF;
import student.ReturnData_STUD;
import student.StrangeThings_STUD;
import tutor.AbstractExpression;
import tutor.Expression;
import tutor.Utils;
import tutor.Utils.State;

import java.util.Arrays;

import static h.Global.RANDOM;
import static h.Global.T;
import static h.H4_Utils.ExpressionMappings.TO_NON_ATOMAR;
import static h.H4_Utils.ExpressionPredicates.ATOMAR;
import static h.H4_Utils.ExpressionPredicates.NON_ATOMAR;
import static h.H4_Utils.ExpressionStreams.expressions;
import static h.H4_Utils.getExpression;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static reference.StrangeThings_REF.evaluateRecursively_REF;
import static student.Main_STUD.*;
import static student.ReturnData_STUD.*;
import static student.StrangeThings_STUD.*;
import static tutor.Utils.Messages.*;
import static tutor.Utils.TestCollection.Mode.SHOW_ALL;
import static tutor.Utils.TestCollection.test;
import static tutor.Utils.iterate;
import static tutor.Utils.repeat;


public class H4_Tests {

    @Nested
    @TestForSubmission("h06")
    @DisplayName("H4.1 | class <ReturnData>")
    public static class H4_1 {

        @BeforeAll
        public static void beforeAll() {
            RETURN_DATA.assureClassResolved();
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            test()
                .add(RETURN_DATA::assureClassResolved).terminateOnException()
                .add(RETURN_DATA::assertIsPlainClass)
                .add(RETURN_DATA::assertDoesNotImplementAnyInterfaces)
                .add(RETURN_DATA::assertAccessModifier)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("2 | Attribute <result>")
        public void test2() {
            test()
                .add(RESULT::assureExists).terminateOnException()
                .add(RESULT::assertModifiers)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("3 | Attribute <nextIndex>")
        public void test3() {
            test()
                .add(NEXT_INDEX::assureExists).terminateOnException()
                .add(NEXT_INDEX::assertModifiers)
                .run(SHOW_ALL);
        }

    }

    @Nested
    @TestForSubmission("h06")
    public static class H4_2 {


        @BeforeAll
        static void beforeAll() {
            test()
                .add(RETURN_DATA::assureClassResolved)
                .add(RESULT::assureExists)
                .add(NEXT_INDEX::assureExists)
                .add(EVALUATE::assureMethodResolved)
                .add(EVALUATE_RECURSIVELY::assureMethodResolved)
                .add(STRANGE_THINGS::assureClassResolved)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("1 | declaration of <evaluate(char[])")
        public void test1() {
            test()
                .add(EVALUATE::assertAccessModifier)
                .add(EVALUATE::assertParametersMatch)
                .add(EVALUATE::assertReturnType)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("2 | implementation of <evaluate(char[])")
        public void test2() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                var lastArray = new State();
                when(StrangeThings_STUD.evaluateRecursively_STUD()).then(a -> {
                    char[] array = a.getArgument(0);
                    int index = a.getArgument(1);
                    test("evaluate")
                        .add(() -> assertArrayEquals(lastArray.get(), array, incorrectParameter("array")), Arrays.toString(array))
                        .add(() -> assertEquals(0, index, incorrectParameter("nextIndex")), Arrays.toString(array))
                        .run(SHOW_ALL);
                    // noinspection ConstantConditions
                    return evaluateRecursively_REF(array, index).getActualObject();
                });
                repeat(T, () -> {
                    var array = getExpression(RANDOM).characters();
                    lastArray.set(array);
                    evaluate_STUD(array);
                });
            }
        }

        @Test
        @DisplayName("3 | declaration of <evaluateRecursively(char[],int)")
        public void test3() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                test()
                    .add(EVALUATE_RECURSIVELY::assertAccessModifier)
                    .add(EVALUATE_RECURSIVELY::assertParametersMatch)
                    .add(EVALUATE_RECURSIVELY::assertReturnType)
                    .run(SHOW_ALL);
            }
        }

        // Checks if evaluateRecursively calls itself if the sub-expression is atomic.
        @Test
        @DisplayName("4 | no recursive call for atomic sub-expression")
        public void test4() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
                    for (AbstractExpression se : iterate(e.stream().filter(ATOMAR))) {
                        State state = new State();
                        when(StrangeThings_STUD.evaluateRecursively_STUD()).then(a -> {
                            if (state.incInt() == 0) return a.callRealMethod();
                            return test("evaluateRecursively").run(() -> fail(unexpectedCall()), e, se.startIndex());
                        });
                        evaluateRecursively_STUD(e.characters(), se.startIndex());
                    }
                }
            }
        }

        // Checks if evaluateRecursively returns the correct object if the sub-expression is atomic.
        @Test
        @DisplayName("5 | returned values for atomic sub-expression")
        public void test5() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
                    for (AbstractExpression se : iterate(e.stream().filter(ATOMAR))) {
                        ReturnData_STUD d = evaluateRecursively_STUD(e.characters(), se.startIndex());
                        test("evaluateRecursively")
                            .add(() -> assertEquals(se.nextIndex(), d.getNextIndex(), incorrectAttribute("nextIndex")), e, se.startIndex())
                            .add(() -> assertEquals(se.getValue(), d.getResult(), incorrectAttribute("result")), e, se.startIndex())
                            .run(SHOW_ALL);
                    }
                }
            }
        }

        // Checks if evaluateRecursively calls itself correctly to evaluate the first sub-expression.
        @Test
        @DisplayName("7 | 1st recursive call for 1st non-atomic sub-expressions")
        public void test7() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
                    for (Expression se : iterate(e.stream().filter(NON_ATOMAR).map(TO_NON_ATOMAR))) {
                        var state = new State();
                        when(StrangeThings_STUD.evaluateRecursively_STUD()).then(a -> {
                            int index = a.getArgument(1);
                            state.incInt();
                            if (state.getInt() == 1) {
                                return a.callRealMethod();
                            } else if (state.getInt() == 2) { // recursive call 1
                                test("evaluateRecursively")
                                    .run(() -> assertEquals(se.expression1().startIndex, index, incorrect("index of first call")), e, se.startIndex());
                            }
                            return e.evaluate(index).getActualObject();
                        });
                        evaluateRecursively_STUD(e.characters(), se.startIndex());
                        if (state.getInt() < 2) fail("missing call");
                    }
                }
            }
        }

        // Checks if evaluateRecursively calls itself correctly to evaluate the second sub-expression.
        @Test
        @DisplayName("8 | 2nd recursive call for 2nd non-atomic sub-expressions")
        public void test8() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
                    for (Expression se : iterate(e.stream().filter(NON_ATOMAR).map(TO_NON_ATOMAR))) {
                        var state = new State();
                        when(StrangeThings_STUD.evaluateRecursively_STUD()).then(a -> {
                            int index = a.getArgument(1);
                            state.incInt();
                            if (state.getInt() == 1) {
                                return a.callRealMethod();
                            } else if (state.getInt() == 3) { // recursive call 2
                                test("evaluateRecursively").add(() -> assertEquals(se.expression2().startIndex, index, incorrect("index of second call")), e, se.startIndex()).run();
                                return se.evaluate().getActualObject();
                            }
                            return e.evaluate(index).getActualObject();
                        });
                        evaluateRecursively_STUD(e.characters(), se.startIndex());
                        if (state.getInt() < 3) fail("missing call");
                    }
                }
            }
        }

        // Checks if evaluateRecursively returns the correct object if the sub-expression is non-atomic.
        @Test
        @DisplayName("9 | returned value for non-atomic sub-expression")
        public void test9() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
                    for (Expression se : iterate(e.stream().filter(NON_ATOMAR).map(TO_NON_ATOMAR))) {
                        var state = new State();
                        when(StrangeThings_STUD.evaluateRecursively_STUD()).then(a -> {
                            int index = a.getArgument(1);
                            state.incInt();
                            if (state.getInt() == 1) return a.callRealMethod();
                            return se.evaluate(index).getActualObject();
                        });
                        var expected = se.evaluate();
                        var actual = evaluateRecursively_STUD(e.characters(), se.startIndex());
                        test("evaluateRecursively")
                            .add(() -> assertEquals(expected.getResult(), actual.getResult(), Utils.Messages.incorrectAttribute("result")), e, se.startIndex())
                            .add(() -> assertEquals(expected.getNextIndex(), actual.getNextIndex(), Utils.Messages.incorrectAttribute("nextIndex")), e, se.startIndex())
                            .run();
                    }
                }
            }
        }

        // Checks if evaluateRecursively returns always the correct object.
        // Contrary to other tests this test also checks atomic expressions.
        @Test
        @DisplayName("10 | returned values (including atomic expressions)")
        public void test10() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                for (AbstractExpression e : iterate(expressions(RANDOM).limit(100).sorted())) {
                    var expected = e.evaluate();
                    var actual = evaluateRecursively_STUD(e.characters(), 0);
                    test("evaluateRecursively")
                        .add(() -> assertEquals(expected.getResult(), actual.getResult(), Utils.Messages.incorrectAttribute("result")), e, 0)
                        .add(() -> assertEquals(expected.getNextIndex(), actual.getNextIndex(), Utils.Messages.incorrectAttribute("nextIndex")), e, 0)
                        .run();
                }
            }
        }
    }

    @Nested
    public static class H_4_3 {

        @BeforeAll
        static void beforeAll() {
            test()
                .add(RETURN_DATA::assureClassResolved)
                .add(RESULT::assureExists)
                .add(NEXT_INDEX::assureExists)
                .add(STRANGE_THINGS::assureClassResolved)
                .add(TEST_EVALUATE::assureMethodResolved)
                .add(EVALUATE::assureMethodResolved)
                .add(EVALUATE_RECURSIVELY::assureMethodResolved)
                .add(MAIN::assureClassResolved)
                .add(METHOD_MAIN::assureMethodResolved)
                .run(SHOW_ALL);
        }


        @Test
        @DisplayName("1 | declaration")
        public void test1() {
            var ignored = StrangeThings_STUD.getStrangeThingsMock();
            {
                test()
                    .add(EVALUATE_RECURSIVELY::assertAccessModifier)
                    .add(EVALUATE_RECURSIVELY::assertParametersMatch)
                    .add(EVALUATE_RECURSIVELY::assertReturnType).run();
            }
        }

        @Test
        @DisplayName("2 | correct values")
        public void test2() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                var counter = new State();
                when(StrangeThings_STUD.evaluateRecursively_STUD()).then(a -> {
                    char[] array = a.getArgument(0);
                    int expected = a.getArgument(1);
                    int actual = StrangeThings_REF.evaluateRef(array);
                    if (actual == expected)
                        counter.incInt();
                    return actual != expected;
                });
                mainMock();
                if (counter.getInt() < 5)
                    assertEquals(5, counter.getInt(), "too few tests of this type");
            }
        }

        @Test
        @DisplayName("3 | incorrect values")
        public void test3() {
            var STRANGE_THINGS_MOCK = StrangeThings_STUD.getStrangeThingsMock();
            {
                var counter = new State();
                when(StrangeThings_STUD.evaluateRecursively_STUD()).then(a -> {
                    char[] array = a.getArgument(0);
                    int expected = a.getArgument(1);
                    int actual = StrangeThings_REF.evaluateRef(array);
                    if (actual != Integer.MIN_VALUE && actual != expected)
                        counter.incInt();
                    return actual != expected;
                });
                mainMock();
                if (counter.getInt() < 5)
                    assertEquals(5, counter.getInt(), "too few tests of this type");
            }
        }
    }
}
