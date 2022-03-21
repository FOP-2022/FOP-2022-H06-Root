package reference;

import student.AbstractTraverser_STUD;

public class UpTraverser_REF extends AbstractTraverser_STUD {

    @Override
    public int getFirstIndex(double[] array) {
        return 0;
    }

    @Override
    public int getNextIndex(int index) {
        return index + 1;
    }
}
