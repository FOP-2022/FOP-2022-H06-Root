package tutor;

import com.google.common.collect.Streams;
import h06.ReturnData;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static h06.Utils.Messages.invalidIndex;
import static org.junit.jupiter.api.Assertions.fail;

public class Expression extends AbstractExpression {

    private final AbstractExpression e1, e2;

    public Expression(int startIndex, AbstractExpression e1, AbstractExpression e2) {
        super(startIndex);
        this.e1 = e1;
        this.e2 = e2;
    }

    public AbstractExpression expression1() {
        return e1;
    }

    public AbstractExpression expression2() {
        return e2;
    }

    @Override
    public Stream<AbstractExpression> stream() {
        return Streams.concat(Stream.of(this), e1.stream(), e2.stream());
    }

    @Override
    public int getValue() {
        return e1.getValue() - e2.getValue();
    }

    @Override
    public boolean isValidIndex(int index) {
        if (index >= e2.startIndex)
            return e2.isValidIndex(index);
        else if (index >= e1.startIndex)
            return e1.isValidIndex(index);
        return index == startIndex;
    }

    @Override
    public ReturnData evaluate(int index) {
        if (index < startIndex)
            fail(invalidIndex(index));
        if (index >= e2.startIndex)
            return e2.evaluate(index);
        else if (index >= e1.startIndex)
            return e1.evaluate(index);

        ReturnData d = new ReturnData();
        d.nextIndex = startIndex + length();
        d.result = getValue();
        return d;
    }

    @Override
    public List<Character> getCharacterList() {
        List<Character> l = new LinkedList<>();
        l.add('-');
        l.addAll(e1.getCharacterList());
        l.addAll(e2.getCharacterList());
        return l;
    }


}
