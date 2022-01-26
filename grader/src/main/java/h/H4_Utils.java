package h;

import tutor.AbstractExpression;
import tutor.AtomarExpression;
import tutor.Expression;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class H4_Utils {


    public static final Predicate<AbstractExpression> PREDICATE = e -> e.getCharacterList().size() <= 20;

    public static AbstractExpression getExpression(Random r) {
        return getExpression(0, r);
    }

    private static AbstractExpression getExpression(int index, Random r) {
        boolean isAtomar = r.nextBoolean();
        if (isAtomar) {
            return new AtomarExpression(index, (char) (48 + r.nextInt(10)));
        }
        AbstractExpression e;
        return new Expression(index, e = getExpression(index + 1, r), getExpression(index + 1 + e.length(), r));
    }

    public interface ExpressionStreams {

        static Stream<AbstractExpression> expressions(Random r) {
            return Stream.generate(() -> getExpression(r)).filter(PREDICATE);
        }

    }

    public interface CharacterStreams {

        static Stream<Character> ofDigit(Random r) {
            return Stream.of((char) (48 + r.nextInt(10)));
        }

        static Stream<Character> ofOperator(Random ignoredRandom) {
            return Stream.of('-');
        }

        static Stream<Character> ofExpression(Random r) {
            if (r.nextBoolean()) { // non-atomic sub-expression
                return Stream.of(
                        ofOperator(r),
                        ofExpression(r),
                        ofExpression(r))
                    .flatMap(Function.identity());
            } else { // atomic sub-expression
                return ofDigit(r);
            }
        }
    }

    public interface CharacterPredicates {

        Predicate<Character> IS_DIGIT = Character::isDigit;
        Predicate<Character> IS_OPERATOR = c -> c == '-';

    }

    public interface ExpressionPredicates {

        Predicate<AbstractExpression> ATOMAR = e -> e instanceof AtomarExpression;
        Predicate<AbstractExpression> NON_ATOMAR = ATOMAR.negate();

    }

    public interface ExpressionMappings {

        Function<AbstractExpression, Expression> TO_NON_ATOMAR = e -> (Expression) e;

    }

    public interface ExpressionCollectors {

        static <A extends Comparable<? super A>> Collector<A, ?, List<A>> toSortedList() {
            return Collector.of(
                () -> new LinkedList<A>(),
                List::add,
                (a, b) -> null,
                l -> {
                    Collections.sort(l);
                    return l;
                }
            );

        }

    }


}
