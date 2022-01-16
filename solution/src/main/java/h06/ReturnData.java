package h06;

public class ReturnData {

    public int result, nextIndex;

    public ReturnData() {

    }

    // optional
    public ReturnData(int result, int nextIndex) {
        this.result = result;
        this.nextIndex = nextIndex;
    }

    @Override
    public String toString() {
        return "ReturnData{" +
            "result=" + result +
            ", nextIndex=" + nextIndex +
            '}';
    }
}
