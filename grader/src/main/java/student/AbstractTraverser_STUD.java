package student;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public abstract class AbstractTraverser_STUD implements Traverser_STUD {

    public final Object object;

    public AbstractTraverser_STUD() {
        this.object = TRAVERSER.assureClassResolved().getNewInstance();
        when(TRAVERSER_GET_FIRST_INDEX.assureMethodResolved().invoke(object, any(double[].class))).then(a -> getFirstIndex(a.getArgument(0)));
        when(TRAVERSER_GET_NEXT_INDEX.assureMethodResolved().invoke(object, anyInt())).then(a -> getNextIndex(a.getArgument(0)));
    }

    @Override
    public Object getActualObject() {
        return object;
    }
}
