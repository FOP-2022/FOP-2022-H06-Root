package h;

import org.junit.jupiter.api.*;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import reference.UpTraverser_REF;
import spoon.support.reflect.code.CtForEachImpl;
import spoon.support.reflect.code.CtForImpl;
import spoon.support.reflect.code.CtWhileImpl;
import student.DownTraverser_STUD;
import student.RandomTraverser;
import student.StrangeThings_STUD;
import student.UpTraverser_STUD;

import java.util.Arrays;
import java.util.List;

import static h.Global.RANDOM;
import static h.Global.T;
import static h.H3_Utils.createDoubleArray;
import static h.H3_Utils.createEmptyOutput;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static reference.StrangeThings_REF.transformArray_REF;
import static student.DownTraverser_STUD.DOWN_TRAVERSER;
import static student.StrangeThings_STUD.*;
import static student.UpTraverser_STUD.*;
import static tutor.Utils.Messages.*;
import static tutor.Utils.TestCollection.Mode.SHOW_ALL;
import static tutor.Utils.TestCollection.Mode.SHOW_FIRST;
import static tutor.Utils.TestCollection.test;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class H3_Tests {

    @BeforeAll
    public void beforeAll() {
        test()
            .add(TRAVERSER::assureClassResolved)
            .terminateOnException()
            .add(TRAVERSER_GET_FIRST_INDEX::assureMethodResolved)
            .add(TRAVERSER_GET_NEXT_INDEX::assureMethodResolved)
            .run(SHOW_ALL);
    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("1 | Interface <Traverser>")
    public static class H3_1_1 {

        @BeforeAll
        public static void beforeAll() {
            TRAVERSER.assureClassResolved();
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            test()
                .add(TRAVERSER::assertAccessModifier)
                .add(TRAVERSER::assertIsInterface)
                .run(SHOW_ALL);
        }
    }

    @Nested
    @DisplayName("2 | Class <UpTraverser>")
    public static class H3_1_2 {

        @BeforeAll
        public static void beforeAll() {
            UP_TRAVERSER.assureClassResolved();
        }

        @Test
        @DisplayName("1 | Access Modifiers")
        public void test1() {
            UP_TRAVERSER.assertAccessModifier();
        }

        @Test
        @DisplayName("2 | Interface")
        public void test2() {
            UP_TRAVERSER.assertImplementsInterfaces(List.of(TRAVERSER_MATCHER));
        }

        @Test
        @DisplayName("3 | Method <getFirstIndex(double[])>")
        public void test3() {
            UpTraverser_STUD traverser = new UpTraverser_STUD();
            for (int r = 0; r < T; r++) {
                var array = createDoubleArray(RANDOM);
                var index = traverser.getFirstIndex(array);
                assertEquals(0, index, incorrect("first index from <UpTraverser>"));
            }
        }

        @Test
        @DisplayName("4 | Method <getNextIndex(int)>")
        public void test4() {
            UpTraverser_STUD traverser = new UpTraverser_STUD();
            for (int r = 0; r < T; r++) {
                var index = RANDOM.nextInt(100);
                var nextIndex = traverser.getNextIndex(index);
                assertEquals(index + 1, nextIndex, incorrect(String.format("next index from <UpTraverser> for <%s>",
                    index)));
            }
        }

    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("3 | Class <DownTraverser>")
    public static class H3_1_3 {

        @BeforeAll
        public static void beforeEach() {
            DOWN_TRAVERSER.assureClassResolved();
        }

        @Test
        @DisplayName("1 | Access Modifiers")
        public void test1() {
            DOWN_TRAVERSER.assertAccessModifier();
        }

        @Test
        @DisplayName("2 | Interface")
        public void test2() {
            DOWN_TRAVERSER.assertImplementsInterfaces(List.of(TRAVERSER_MATCHER));
        }

        @Test
        @DisplayName("3 | Method <getFirstIndex(double[])>")
        public void test3() {
            var traverser = new DownTraverser_STUD();
            for (int r = 0; r < T; r++) {
                var array = createDoubleArray(RANDOM);
                var index = traverser.getFirstIndex(array);
                assertEquals(array.length - 1, index, incorrect("first index from <DownTraverser>"));
            }
        }

        @Test
        @DisplayName("4 | Method <getNextIndex(int)>")
        public void test4() {
            var traverser = new DownTraverser_STUD();
            for (int r = 0; r < T; r++) {
                var index = RANDOM.nextInt(100);
                var nextIndex = traverser.getNextIndex(index);
                assertEquals(index - 1, nextIndex, incorrect(String.format("next index from <DownTraverser> for <%s>"
                    , index)));
            }
        }

    }

    @Nested
    @DisplayName("4 | Method <transformArrayIteratively(double[])>")
    public static class H3_2_1 {

        @BeforeAll
        public static void beforeAll() {
            test()
                .add(STRANGE_THINGS::assureClassResolved)
                .add(TRANSFORM_ARRAY_ITERATIVELY::assureMethodResolved)
                .add(TRAVERSER::assureClassResolved)
                .run(SHOW_FIRST);
        }

        @Test
        @DisplayName("1 | Access Modifiers")
        public void test1() {
            TRANSFORM_ARRAY_ITERATIVELY.assertAccessModifier();
        }

        @Test
        @DisplayName("2 | Parameter and Return Types")
        public void test2() {
            test()
                .add(TRANSFORM_ARRAY_ITERATIVELY::assertParametersMatch)
                .add(TRANSFORM_ARRAY_ITERATIVELY::assertReturnType)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("3 | No Recursion")
        public void test3() {
            TRANSFORM_ARRAY_ITERATIVELY.assertNotRecursive(2);
        }

        // Checks the array access order of method <transformArrayIteratively>
        // using a custom implementation of <Traverser> (<RandomTraverser>).
        @Test
        @DisplayName("4 | Array Access Order")
        public void test4() {
            for (int r = 0; r < T; r++) {
                var traverser = new RandomTraverser(RANDOM);
                var expected = traverser.getExpectedArrayCallOrder(traverser.array);
                var result = transformArrayIteratively_STUDENT(traverser.array, traverser);
                var actual = traverser.getActualArrayCallOrder(result);
                assertEquals(expected, actual, incorrectOrderOfWhenUsing("array accesses", "using custom " +
                    "implementation of <Traverser>"));
            }
        }

        // Checks the array access order of method transformArrayRecursively
        // using a custom implementation of <Traverser> (<RandomTraverser>).
        @Test
        @DisplayName("5 | Result Array")
        public void test5() {
            for (int r = 0; r < T; r++) {
                var traverser = new UpTraverser_REF();
                var array = RANDOM.ints(-10, 10).limit(10).mapToDouble(i -> i).toArray();
                var expected = stream(array).map(i -> 2 * i + 3.14).toArray();
                var actual = transformArrayIteratively_STUDENT(array, traverser);
                assertEquals(Arrays.toString(expected), Arrays.toString(actual), incorrectValuesInWhenUsing("array",
                    "using reference implementation of <UpTraverser>"));
            }
        }


    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("2 | Method <transformArrayRecursively(double[])>")
    public static class H3_2_2 {

        @BeforeAll
        public static void beforeAll() {
            test()
                .add(STRANGE_THINGS::assureClassResolved)
                .add(TRANSFORM_ARRAY_RECURSIVELY::assureMethodResolved)
                .add(TRAVERSER::assureClassResolved)
                .run(SHOW_FIRST);
        }

        @Test
        @DisplayName("1 | Access Modifiers")
        public void test1() {
            TRANSFORM_ARRAY_RECURSIVELY.assertAccessModifier();
        }

        @Test
        @DisplayName("2 | Parameter and Return Types")
        public void test2() {
            test()
                .add(TRANSFORM_ARRAY_RECURSIVELY::assertParametersMatch)
                .add(TRANSFORM_ARRAY_RECURSIVELY::assertReturnType)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("3 | No Recursion")
        public void test3() {
            TRANSFORM_ARRAY_ITERATIVELY.assertNotRecursive(2);
        }

        // Checks the array access order of method transformArrayRecursively.
        @Test
        @DisplayName("4 | Array Access Order")
        public void test4() {
            for (int r = 0; r < T; r++) {
                var traverser = new RandomTraverser(RANDOM);
                var expected = traverser.getExpectedArrayCallOrder(traverser.array);
                var result = transformArrayRecursively_STUDENT(traverser.array, traverser);
                var actual = traverser.getActualArrayCallOrder(result);
                assertEquals(expected, actual, incorrectOrderOfWhenUsing("array accesses", "using custom " +
                    "implementation of <Traverser>"));
            }
        }

        @Test
        @DisplayName("5 | Result Array")
        public void test5() {
            for (int r = 0; r < T; r++) {
                var traverser = new UpTraverser_REF();
                var array = RANDOM.ints(-10, 10).limit(10).mapToDouble(i -> i).toArray();
                var expected = transformArray_REF(array);
                var actual = transformArrayRecursively_STUDENT(array, traverser);
                assertEquals(Arrays.toString(expected), Arrays.toString(actual), incorrectValuesInWhenUsing("array",
                    "using reference implementation of <UpTraverser>"));
            }
        }

        @Test
        @DisplayName("6 | No Iterative Elements")
        public void test6() {
            TRANSFORM_ARRAY_RECURSIVELY.assertConstructsNotUsed(List.of(CtForImpl.class, CtForEachImpl.class,
                CtWhileImpl.class));
        }

    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("3 | Method <doTheRecursion(double[],double[],Traverser,int)>")
    public static class H3_2_3 {

        @BeforeAll
        public static void beforeAll() {
            test()
                .add(STRANGE_THINGS::assureClassResolved)
                .add(DO_THE_RECURSION::assureMethodResolved)
                .add(TRAVERSER::assureClassResolved)
                .run(SHOW_ALL);
        }


        @Test
        @DisplayName("1 | Access Modifiers")
        public void test1() {
            DO_THE_RECURSION.assertAccessModifier();
        }

        @Test
        @DisplayName("2 | Parameter and Return Types")
        public void test2() {
            test()
                .add(DO_THE_RECURSION::assertParametersMatch)
                .add(DO_THE_RECURSION::assertReturnType)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("3 | Return Values")
        public void test3() {
            var ignored = StrangeThings_STUD.getStrangeThingsMock();
            {
                range(0, T).forEach(i -> {
                        var input = createDoubleArray(RANDOM);
                        var inputSave = Arrays.copyOf(input, input.length);
                        var output = createEmptyOutput(input);
                        var outputSave = Arrays.copyOf(output, output.length);
                        var traverser = new UpTraverser_REF();
                        int index = 0;
                        doTheRecursion_STUD(input, output, traverser, index);
                        var outputRef = transformArray_REF(input);
                        test("doTheRecursion").run(
                            () -> assertArrayEquals(output, outputRef, incorrectReturnedObject()),
                            Arrays.toString(inputSave),
                            Arrays.toString(outputSave), traverser.getClass().getSimpleName(),
                            0);
                    }
                );
            }
        }

    }

}
