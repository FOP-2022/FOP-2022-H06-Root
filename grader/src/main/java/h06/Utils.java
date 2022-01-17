package h06;

import org.opentest4j.AssertionFailedError;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public interface Utils {

    static <T> Iterable<T> iterate(Stream<T> s) {
        return s::iterator;
    }

    interface Messages {


        static String incorrect(String name) {
            return String.format("%s is not correct", name);
        }

        static String incorrectAttribute(String attributeName) {
            return incorrect(String.format("value of attribute <%s>", attributeName));
        }

        static String incorrectParameter(String parameterName) {
            return incorrect(String.format("value of parameter <%s>", parameterName));
        }

        static String notInRange(String name) {
            return String.format("%s is not in range", name);
        }

        static String indexNotInRange() {
            return notInRange("index");
        }

        static String invalid(String key, Object value) {
            return String.format("invalid %s ==> <%s>", key, value);
        }

        static String invalidIndex(int value) {
            return invalid("index", value);
        }

        static String unexpectedCall() {
            return "unexpected call";
        }


    }

    class TestCollection {

        private final String methodName;
        private final List<Runnable> runnables = new LinkedList<>();
        private final String input;

        private TestCollection(String methodName, Object... input) {
            this.methodName = methodName;
            this.input = Arrays.stream(input).map(Object::toString).collect(Collectors.joining(", "));
        }


        public static TestCollection test(String methodName, Object... input) {
            return new TestCollection(methodName, input);
        }

        public static TestCollection test() {
            return test(null);
        }


        public TestCollection nextTest(Runnable runnable) {
            runnables.add(runnable);
            return this;
        }

        public TestCollection lastTest(Runnable runnable) {
            runnables.add(runnable);
            run();
            return this;
        }

        public <T> T run() {
            var es = new AssertionFailedErrors(methodName, input);

            for (Runnable r : runnables) {
                try {
                    r.run();
                } catch (AssertionFailedError e) {
                    es.add(e);
                }
            }
            if (!es.isEmpty()) {
                throw es;
            }
            return null;
        }

    }

    class AssertionFailedErrors extends AssertionFailedError {

        private final List<AssertionFailedError> l = new LinkedList<>();

        private final String methodName;
        private final String input;

        public AssertionFailedErrors(String methodName, String input) {
            this.methodName = methodName;
            this.input = input;
        }

        public void add(AssertionFailedError error) {
            l.add(error);
        }

        public boolean isEmpty() {
            return l.isEmpty();
        }

        @Override
        public String getMessage() {
            var number = l.size();
            var messages = IntStream.range(0, number).mapToObj(i -> String.format("(%d) %s", i + 1, l.get(i).getMessage())).collect(Collectors.joining(", "));
            StringBuilder b = new StringBuilder();
            if (methodName != null)
                b.append(methodName);
            if (input != null)
                b.append(String.format("(%s)", input));
            if (!b.isEmpty())
                b.append(": ");
            b.append(messages);
            return b.toString();
        }
    }

    class State {


        private int state = 0;

        public int get() {
            return state;
        }

        public int inc() {
            return state++;
        }

        public void set(int state) {
            this.state = state;
        }

        public boolean is(int state) {
            return this.state == state;
        }

    }


}
