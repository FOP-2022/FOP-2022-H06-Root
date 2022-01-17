package h06;

import h06.Utils.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tutor.AbstractExpression;
import tutor.Expression;

import java.util.Random;

import static h06.H4_2_Utils.ExpressionMappings.TO_NON_ATOMAR;
import static h06.H4_2_Utils.ExpressionPredicates.ATOMAR;
import static h06.H4_2_Utils.ExpressionPredicates.NON_ATOMAR;
import static h06.H4_2_Utils.ExpressionStreams.expressions;
import static h06.Utils.Messages.*;
import static h06.Utils.TestCollection.test;
import static h06.Utils.iterate;
import static org.junit.jupiter.api.Assertions.*;
import static tutor.StrangeThingsMock.*;


public class H4_2 {

    static final int T = 25;

    static final Random RANDOM = new Random();


    static MockedStatic.Verification CALLER;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void beforeEach() {
        RANDOM.setSeed(42);
        clear();
    }

    @Test
    public void test1() {

    }

    // Checks if evaluateRecursively calls itself if the sub-expression is atomic.
    @Test
    public void test2() throws Exception {
        for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
            for (AbstractExpression se : iterate(e.stream().filter(ATOMAR))) {
                State state = new State();
                MOCK.when(EVALUATE_RECURSIVELY_VERIFICATION).then(a -> {
                    state.inc();
                    if (state.get() == 1)
                        return a.callRealMethod();
                    return test("evaluateRecursively", e, se.startIndex())
                        .nextTest(() -> fail(unexpectedCall()));
                });
                evaluateRecursively(e.characters(), se.startIndex());
            }
        }
    }

    // Checks if evaluateRecursively returns the correct object if the sub-expression is atomic.
    @Test
    public void test3() throws Exception {
        for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
            for (AbstractExpression se : iterate(e.stream().filter(ATOMAR))) {
                ReturnData d = evaluateRecursively(e.characters(), se.startIndex());
                test("evaluateRecursively", e, se.startIndex())
                    .nextTest(() -> assertEquals(se.nextIndex(), d.nextIndex, incorrectAttribute("nextIndex")))
                    .nextTest(() -> assertEquals(se.getValue(), d.result, incorrectAttribute("result")))
                    .run();
            }
        }
    }


    // Checks if evaluateRecursively calls itself to evaluate the first sub-expression.
    @Test
    public void test4() throws Exception {
        for (Expression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).map(TO_NON_ATOMAR).sorted())) {
            for (Expression se : iterate(e.stream().filter(NON_ATOMAR).map(TO_NON_ATOMAR))) {
                State state = new State();
                MOCK.when(EVALUATE_RECURSIVELY_VERIFICATION).then(a -> {
                    state.inc();
                    if (state.get() == 1)
                        return a.callRealMethod();
                    int index = a.getArgument(1);
                    test("evaluateRecursively", e, index)
                        .nextTest(() -> assertTrue(index < e.length(), invalidIndex(index)))
                        .run();
                    return e.evaluate(index);
                });
                evaluateRecursively(e.characters(), se.startIndex());
                assertTrue(state.get() > 1, "no call");
            }
        }
    }

    // Checks if evaluateRecursively calls itself correctly to evaluate the first sub-expression.
    @Test
    public void test5() throws Exception {
        for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
            for (Expression se : iterate(e.stream().filter(NON_ATOMAR).map(TO_NON_ATOMAR))) {
                var state = new State();
                MOCK.when(EVALUATE_RECURSIVELY_VERIFICATION).then(a -> {
                    int index = a.getArgument(1);
                    state.inc();
                    if (state.get() == 1) {
                        return a.callRealMethod();
                    } else if (state.get() == 2) { // recursive call 1
                        test("evaluateRecursively", e, se.startIndex())
                            .nextTest(() -> assertEquals(se.expression1().startIndex, index, incorrect("index of first call")))
                            .run();
                    }
                    return e.evaluate(index);
                });
                evaluateRecursively(e.characters(), se.startIndex());
                if (state.get() < 2)
                    fail("missing call");
                clear();
            }
        }
    }

    // Checks if evaluateRecursively calls itself correclty to evaluate the second sub-expression.
    @Test
    public void test6() throws Exception {
        for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
            for (Expression se : iterate(e.stream().filter(NON_ATOMAR).map(TO_NON_ATOMAR))) {
                var state = new State();
                MOCK.when(EVALUATE_RECURSIVELY_VERIFICATION).then(a -> {
                    int index = a.getArgument(1);
                    state.inc();
                    if (state.get() == 1) {
                        return a.callRealMethod();
                    } else if (state.get() == 3) { // recursive call 2
                        test("evaluateRecursively", e, se.startIndex())
                            .nextTest(() -> assertEquals(se.expression2().startIndex, index, incorrect("index of second call")))
                            .run();
                        return se.evaluate();
                    }
                    return e.evaluate(index);
                });
                evaluateRecursively(e.characters(), se.startIndex());
                if (state.get() < 3)
                    fail("missing call");
                clear();
            }
        }
    }

    // Checks if evaluateRecursively returns the correct object if the sub-expression is non-atomic.
    @Test
    public void test7() throws Exception {
        for (AbstractExpression e : iterate(expressions(RANDOM).filter(NON_ATOMAR).limit(T).sorted())) {
            for (Expression se : iterate(e.stream().filter(NON_ATOMAR).map(TO_NON_ATOMAR))) {
                var state = new State();
                MOCK.when(EVALUATE_RECURSIVELY_VERIFICATION).then(a -> {
                    int index = a.getArgument(1);
                    state.inc();
                    if (state.get() == 1)
                        return a.callRealMethod();
                    return se.evaluate(index);
                });
                var expected = se.evaluate();
                var actual = evaluateRecursively(e.characters(), se.startIndex());
                test("evaluateRecursively", e, se.startIndex())
                    .nextTest(() -> assertEquals(expected.result, actual.result, Utils.Messages.incorrectAttribute("result")))
                    .nextTest(() -> assertEquals(expected.nextIndex, actual.nextIndex, Utils.Messages.incorrectAttribute("nextIndex")))
                    .run();
                clear();
            }
        }
    }

    @Test
    public void test8() {

    }

    // Checks if evaluateRecursively returns always the correct object.
    // Contrary to other tests this test also checks atomic expressions.
    @Test
    public void test9() throws Exception {
        for (AbstractExpression e : iterate(expressions(RANDOM).limit(100).sorted())) {
            var expected = e.evaluate();
            var actual = evaluateRecursively(e.characters(), 0);
            test("evaluateRecursively", e, 0)
                .nextTest(() -> assertEquals(expected.result, actual.result, Utils.Messages.incorrectAttribute("result")))
                .nextTest(() -> assertEquals(expected.nextIndex, actual.nextIndex, Utils.Messages.incorrectAttribute("nextIndex")))
                .run();
            clear();
        }
    }


}
