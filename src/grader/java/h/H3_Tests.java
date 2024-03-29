package h;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import reference.UpTraverser_REF;
import spoon.support.reflect.code.CtForEachImpl;
import spoon.support.reflect.code.CtForImpl;
import spoon.support.reflect.code.CtWhileImpl;
import student.DownTraverser_STUD;
import student.RandomTraverser;
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
import static student.DownTraverser_STUD.cDownTraverser;
import static student.StrangeThings_STUD.doTheRecursion_STUD;
import static student.StrangeThings_STUD.mDoTheRecursion;
import static student.StrangeThings_STUD.mTransformArrayIteratively;
import static student.StrangeThings_STUD.mTransformArrayRecursively;
import static student.StrangeThings_STUD.resetStrangeThingsMock;
import static student.StrangeThings_STUD.transformArrayIteratively_STUDENT;
import static student.StrangeThings_STUD.transformArrayRecursively_STUDENT;
import static student.Traverser_STUD.cTraverser;
import static student.UpTraverser_STUD.cUpTraverser;
import static tutor.Utils.Messages.incorrect;
import static tutor.Utils.Messages.incorrectOrderOfWhenUsing;
import static tutor.Utils.Messages.incorrectReturnedObject;
import static tutor.Utils.Messages.incorrectValuesInWhenUsing;
import static tutor.Utils.TestCollection.Mode.SHOW_ALL;
import static tutor.Utils.TestCollection.test;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class H3_Tests {

    @TestForSubmission("h06")
    @Nested
    @DisplayName("1 | Interface <Traverser>")
    public class H3_1_1 {

        @Test
        @DisplayName("0 | requirements")
        public void test0() {
            test()
                .add(cTraverser()::assureResolved)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            cTraverser().assertCorrectDeclaration();
        }
    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("2 | Class <UpTraverser>")
    public class H3_1_2 {

        @Test
        @DisplayName("0 | requirements")
        public void test0() {
            test()
                .add(cUpTraverser()::assureResolved)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            cUpTraverser().assertCorrectDeclaration();
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
    public class H3_1_3 {

        @Test
        @DisplayName("0 | requirements")
        public void test0() {
            test()
                .add(cDownTraverser()::assureResolved)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            cDownTraverser().assertCorrectDeclaration();
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

    @TestForSubmission("h06")
    @Nested
    @DisplayName("4 | Method <transformArrayIteratively(double[])>")
    public class H3_2_1 {

        @BeforeEach
        public void beforeEach() {
            resetStrangeThingsMock();
        }

        @Test
        @DisplayName("0 | requirements")
        public void test0() {
            test()
                .add(mTransformArrayIteratively()::assureResolved)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            mTransformArrayIteratively().assertCorrectDeclaration();
        }

        @Test
        @DisplayName("3 | No Recursion")
        public void test3() {
            mTransformArrayIteratively().assertNotRecursive(2); // TODO
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
    public class H3_2_2 {

        @BeforeEach
        public void beforeEach() {
            resetStrangeThingsMock();
        }

        @Test
        @DisplayName("0 | requirements")
        public void test0() {
            test()
                .add(mTransformArrayRecursively()::assureResolved)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            mTransformArrayRecursively().assertCorrectDeclaration();
        }

        @Test
        @DisplayName("3 | No Recursion")
        public void test3() {
            mTransformArrayIteratively().assertNotRecursive(2);
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
            mTransformArrayRecursively().assertConstructsNotUsed(List.of(CtForImpl.class, CtForEachImpl.class,
                CtWhileImpl.class));
        }
    }

    @TestForSubmission("h06")
    @Nested
    @DisplayName("3 | Method <doTheRecursion(double[],double[],Traverser,int)>")
    public class H3_2_3 {

        @BeforeEach
        void beforeEach() {
            RANDOM.setSeed(42);
            resetStrangeThingsMock();
        }

        @Test
        @DisplayName("0 | requirements")
        public void test0() {
            test()
                .add(mDoTheRecursion()::assureResolved)
                .run(SHOW_ALL);
        }

        @Test
        @DisplayName("1 | Declaration")
        public void test1() {
            mDoTheRecursion().assertCorrectDeclaration();
        }

        @Test
        @DisplayName("3 | Return Values")
        public void test3() {
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
            });
        }
    }
}
