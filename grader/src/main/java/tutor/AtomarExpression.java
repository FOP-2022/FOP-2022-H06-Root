package tutor;

import h06.ReturnData;

import java.util.List;
import java.util.stream.Stream;

import static h06.Utils.Messages.indexNotInRange;

public class AtomarExpression extends AbstractExpression {

    private final char c;

    public AtomarExpression(int startIndex, char c) {
        super(startIndex);
        this.c = c;
    }

    @Override
    public int getValue() {
        return Character.getNumericValue(c);
    }

    @Override
    public Stream<AbstractExpression> stream() {
        return Stream.of(this);
    }

    @Override
    public List<Character> getCharacterList() {
        return List.of(c);
    }

    @Override
    public boolean isValidIndex(int index) {
        return startIndex == index;
    }

    @Override
    public ReturnData evaluate(int index) {
        if (index != startIndex)
            throw new RuntimeException(indexNotInRange());
        ReturnData d = new ReturnData();
        d.nextIndex = index + 1;
        d.result = Character.getNumericValue(c);
        return d;
    }
}
