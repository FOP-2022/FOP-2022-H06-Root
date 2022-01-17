package tutor;

import h06.ReturnData;
import h06.StrangeThings;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.MockedStatic.Verification;
import org.powermock.reflect.Whitebox;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.withSettings;

public class StrangeThingsMock {

    public static final MockedStatic<StrangeThings> MOCK;
    public static final Verification EVALUATE_RECURSIVELY_VERIFICATION = () -> Whitebox.invokeMethod(StrangeThings.class, ArgumentMatchers.<byte[]>any(), anyInt());

    static {
        MOCK = mockStatic(
            StrangeThings.class,
            withSettings()
                .defaultAnswer(Answers.CALLS_REAL_METHODS));
    }

    public static ReturnData evaluateRecursively(char[] c, int i) throws Exception {
        return Whitebox.invokeMethod(StrangeThings.class, "evaluateRecursively", c, i);
    }

    public static void clear() {
        MOCK.reset();
    }

}
