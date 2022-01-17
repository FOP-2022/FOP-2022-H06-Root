package h;

import org.sourcegrade.jagr.api.rubric.*;

import static org.sourcegrade.jagr.api.rubric.Grader.descendingPriority;
import static org.sourcegrade.jagr.api.rubric.Grader.testAwareBuilder;
import static org.sourcegrade.jagr.api.rubric.JUnitTestRef.and;
import static org.sourcegrade.jagr.api.rubric.JUnitTestRef.ofMethod;

@RubricForSubmission("h06")
public class H_Rubric implements RubricProvider {

    public static final JUnitTestRef H2_1_0 = JUnitTestRef.or(
        and(
            ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test2")),
            ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test3")),
            ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test4"))
        ),
        and(
            ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test2")),
            ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test3")),
            ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test4"))
        )
    );

    public static final Criterion H2_1_1 = Criterion.builder()
        .shortDescription("Mindestens eine der beiden Methoden ist korrekt deklariert.")
        .grader(
            testAwareBuilder().requirePass(
                    JUnitTestRef.or(
                        ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test1")),
                        ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test1"))
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H2_1_2 = Criterion.builder()
        .shortDescription("Der Rekursionsanker (Fall <code>n<0</code>) funktioniert für mindestens eine der beiden " +
            "Methoden korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        H2_1_0,
                        JUnitTestRef.or(
                            ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test5")),
                            ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test5"))
                        )
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H2_1_3 = Criterion.builder()
        .shortDescription("Die Rekursion (Fall <code>n%2=0</code> und <code>n%2!=0</code>) funktioniert für " +
            "mindestens eine der beiden Methoden korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                and(
                    H2_1_0,
                    JUnitTestRef.or(
                        and(
                            ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test6")),
                            ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test7"))
                        ),
                        and(
                            ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test6")),
                            ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test7"))
                        )
                    )
                )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();
    public static final Criterion H2_1_4 = Criterion.builder()
        .shortDescription("Beide Methoden sind korrekt implementiert.")
        .grader(
            testAwareBuilder().requirePass(
                and(
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test1")),
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test2")),
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test3")),
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test4")),
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test5")),
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test6")),
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test7")),
                    ofMethod(() -> H2_Tests.H2_1_1.class.getMethod("test8")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test1")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test2")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test3")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test4")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test5")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test6")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test7")),
                    ofMethod(() -> H2_Tests.H2_1_2.class.getMethod("test8"))
                )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("2.1 | Methoden computeStrangeValue1 und computeStrangeValue2")
        .addChildCriteria(
            H2_1_1,
            H2_1_2,
            H2_1_3,
            H2_1_4
        ).build();
    public static final JUnitTestRef H2_2_0 = JUnitTestRef.or(
        and(
            ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test2")),
            ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test3")),
            ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test4"))
        ),
        and(
            ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test2")),
            ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test3")),
            ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test4"))
        )

    );

    public static final Criterion H2_2_1 = Criterion.builder()
        .shortDescription("Mindestens eine der beiden Methoden ist korrekt deklariert.")
        .grader(
            testAwareBuilder().requirePass(
                    JUnitTestRef.or(
                        ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test1")),
                        ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test1"))
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H2_2_2 = Criterion.builder()
        .shortDescription("Der Rekursionsanker funktioniert im Fall <code>m<=0</code> für mindestens eine der beiden " +
            "Methoden korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        H2_2_0,
                        JUnitTestRef.or(
                            ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test5")),
                            ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test5"))
                        )
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H2_2_3 = Criterion.builder()
        .shortDescription("Der Rekursionsanker funktioniert im Fall <code>n<=0</code> für mindestens eine der beiden " +
            "Methoden korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        H2_2_0,
                        JUnitTestRef.or(
                            ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test6")),
                            ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test6"))
                        )
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H2_2_4 = Criterion.builder()
        .shortDescription("Die Rekursion funktioniert im Fall <code>m<n</code> für mindestens eine der beiden " +
            "Methoden korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        H2_2_0,
                        JUnitTestRef.or(
                            ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test7")),
                            ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test7"))
                        )
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H2_2_5 = Criterion.builder()
        .shortDescription("Die Rekursion funktioniert im anderen Fall für mindestens eine der beiden Methoden korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        H2_2_0,
                        JUnitTestRef.or(
                            ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test8")),
                            ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test8"))
                        )
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();
    public static final Criterion H2_2_6 = Criterion.builder()
        .shortDescription("Beide Methoden sind korrekt implementiert.")
        .grader(
            testAwareBuilder().requirePass(
                and(
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test1")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test2")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test3")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test4")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test5")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test6")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test7")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test8")),
                    ofMethod(() -> H2_Tests.H2_2_1.class.getMethod("test9")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test1")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test2")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test3")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test4")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test5")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test6")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test7")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test8")),
                    ofMethod(() -> H2_Tests.H2_2_2.class.getMethod("test9"))

                )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();
    public static final Criterion H2_2 = Criterion.builder()
        .shortDescription("2.2 | Methoden whatTheHellIsThat1 und whatTheHellIsThat2")
        .addChildCriteria(
            H2_2_1,
            H2_2_2,
            H2_2_3,
            H2_2_4,
            H2_2_5,
            H2_2_6
        ).build();
    public static final Criterion H3_1_1 = Criterion.builder()
        .shortDescription("Das Interface <code>Traverser</code> ist korrekt deklariert.")
        .grader(
            testAwareBuilder().requirePass(
                    ofMethod(() -> H3_Tests.H3_1_1.class.getMethod("test1")))
                .pointsFailedMin()
                .pointsPassedMax()
                .build()
        )
        .build();

    public static final Criterion H3_1_2 = Criterion.builder()
        .shortDescription("Die Klasse <code>UpTraverser</code> ist korrekt implementiert.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        ofMethod(() -> H3_Tests.H3_1_2.class.getMethod("test1")),
                        ofMethod(() -> H3_Tests.H3_1_2.class.getMethod("test2")),
                        ofMethod(() -> H3_Tests.H3_1_2.class.getMethod("test3")),
                        ofMethod(() -> H3_Tests.H3_1_2.class.getMethod("test4"))
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        )
        .build();

    public static final Criterion H3_1_3 = Criterion.builder()
        .shortDescription("Die Klasse <code>DownTraverser</code> ist korrekt implementiert.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        ofMethod(() -> H3_Tests.H3_1_3.class.getMethod("test1")),
                        ofMethod(() -> H3_Tests.H3_1_3.class.getMethod("test2")),
                        ofMethod(() -> H3_Tests.H3_1_3.class.getMethod("test3")),
                        ofMethod(() -> H3_Tests.H3_1_3.class.getMethod("test4"))
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        )
        .build();


    public static final Criterion H3_1 = Criterion.builder()
        .shortDescription("3.1 | Interface Traverser, Klassen UpTraverser und DownTraverser")
        .addChildCriteria(
            H3_1_1,
            H3_1_2,
            H3_1_3
        )
        .build();

    public static final Criterion H3_2_1 = Criterion.builder()
        .shortDescription("Mindestens eine der beiden Methoden ist korrekt deklariert.")
        .grader(testAwareBuilder().requirePass(
                JUnitTestRef.or(
                    and(
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test1")),
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test2"))
                    ),
                    and(
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test1")),
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test2"))
                    )
                )
            ).pointsFailedMin()
            .pointsPassedMax()
            .build())
        .build();

    public static final Criterion H3_2_2 = Criterion.builder()
        .shortDescription("Die Reihenfolge, in der die Werte aus dem Array abgerufen werden, ist für mindestens eine " +
            "der beiden Methode korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    JUnitTestRef.or(
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test4")),
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test4"))
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H3_2_3 = Criterion.builder()
        .shortDescription("Die Ausgabe ist bei Verwendung von <code>UpTraverser</code> (Referenzimplementierung) für " +
            "mindestens eine der beiden Methoden korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    JUnitTestRef.or(
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test5")),
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test5"))
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H3_2_4 = Criterion.builder()
        .shortDescription("Die Methode <code>transformArrayIteratively</code> ist korrekt implementiert.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test1")),
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test2")),
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test3")),
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test4")),
                        ofMethod(() -> H3_Tests.H3_2_1.class.getMethod("test5"))
                    )
                ).pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H3_2_5 = Criterion.builder()
        .shortDescription("Die Methode <code>transformArrayRecursively</code> ist korrekt implementiert.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test1")),
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test2")),
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test3")),
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test4")),
                        ofMethod(() -> H3_Tests.H3_2_2.class.getMethod("test5"))
                    )
                )
                .pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H3_2_6 = Criterion.builder()
        .shortDescription("Die Methode <code>doTheRecursion</code> ist korrekt implementiert.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        ofMethod(() -> H3_Tests.H3_2_3.class.getMethod("test1")),
                        ofMethod(() -> H3_Tests.H3_2_3.class.getMethod("test2")),
                        ofMethod(() -> H3_Tests.H3_2_3.class.getMethod("test3"))
                    )
                )
                .pointsPassedMax()
                .pointsFailedMin()
                .build()
        ).build();

    public static final Criterion H3_2 = Criterion.builder()
        .shortDescription("3.2 | Methoden <code>transformArrayIteratively</code>, <code>transformArrayRecursively</code> und <code>doTheRecursion</code>")
        .addChildCriteria(
            H3_2_1,
            H3_2_2,
            H3_2_3,
            H3_2_4,
            H3_2_5,
            H3_2_6)
        .build();

    // H4_1

    public static final Criterion H4_1_1 = Criterion.builder()
        .shortDescription("Die Klasse <code>ReturnData</code> ist vollständig korrekt.")
        .grader(
            testAwareBuilder().requirePass(
                    and(
                        ofMethod(() -> H4_Tests.H4_1.class.getMethod("test1")),
                        ofMethod(() -> H4_Tests.H4_1.class.getMethod("test2")),
                        ofMethod(() -> H4_Tests.H4_1.class.getMethod("test3"))))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
        .build();

    public static final Criterion H4_1 = Criterion.builder()
        .shortDescription("4.1 | Klasse <code>ReturnData</code>")
        .addChildCriteria(H4_1_1)
        .build();

    // H4.2

    public static final Criterion H4_2_1 = Criterion.builder()
        .shortDescription("Die Methode <code>evaluate</code> ist vollständig korrekt.")
        .grader(
            testAwareBuilder()
                .requirePass(
                    JUnitTestRef.and(
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test1")),
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test2"))))
                .pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H4_2_2 = Criterion.builder()
        .shortDescription("Die Methode <code>evaluateRecursively</code> ist korrekt deklariert.")
        .grader(
            testAwareBuilder()
                .requirePass(
                    ofMethod(() -> H4_Tests.H4_2.class.getMethod("test3")))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
        .build();

    public static final Criterion H4_2_3 = Criterion.builder()
        .shortDescription(
            "Wenn der aktuelle Teilausdruck atomar ist, wird die Methode nicht rekursiv aufgerufen.")
        .grader(
            testAwareBuilder()
                .requirePass(
                    ofMethod(() -> H4_Tests.H4_2.class.getMethod("test4")))
                .pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H4_2_4 = Criterion.builder()
        .shortDescription(
            "Das für atomare Teilausdrücke zurückgegebene <code>ReturnData</code>-Objekt ist stets korrekt.")
        .grader(
            testAwareBuilder()
                .requirePass(
                    ofMethod(() -> H4_Tests.H4_2.class.getMethod("test5")))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
        .build();

    public static final Criterion H4_2_5 = Criterion.builder()
        .shortDescription("Der erste rekursive Aufruf dient stets zur Auswertung des ersten Teilausdrucks.")
        .grader(
            testAwareBuilder()
                .requirePass(
                    ofMethod(() -> H4_Tests.H4_2.class.getMethod("test7")))
                .pointsFailedMin()
                .pointsPassedMax()
                .build()
        ).build();

    public static final Criterion H4_2_6 = Criterion.builder()
        .shortDescription("Der zweite rekursive Aufruf dient stets zur Auswertung des zweiten Teilausdrucks.")
        .grader(
            descendingPriority(
                testAwareBuilder()
                    .requirePass(
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test8")))
                    .pointsFailedMin()
                    .pointsPassedMax()
                    .build()))
        .build();

    public static final Criterion H4_2_7 = Criterion.builder()
        .shortDescription(
            "Das für nicht-atomare Teilausdrücke zurückgegebene <code>ReturnData</code>-Objekt ist stets korrekt.")
        .grader(
            descendingPriority(
                testAwareBuilder()
                    .requirePass(
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test9")))
                    .pointsFailedMin()
                    .pointsPassedMax()
                    .build()))
        .build();

    public static final Criterion H4_2_8 = Criterion.builder()
        .shortDescription("Die Methode <code>evaluateRecursively</code> ist korrekt implementiert.")
        .grader(
            testAwareBuilder()
                .requirePass(
                    JUnitTestRef.or(
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test3")),
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test4")),
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test5")),
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test7")),
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test8")),
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test9")),
                        ofMethod(() -> H4_Tests.H4_2.class.getMethod("test8")))
                )
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
        .build();

    public static final Criterion H4_2 = Criterion.builder()
        .shortDescription("4.2 | Methoden evaluate und evaluateRecursively")
        .addChildCriteria(
            H4_2_1,
            H4_2_2,
            H4_2_3,
            H4_2_4,
            H4_2_5,
            H4_2_6,
            H4_2_7,
            H4_2_8)
        .build();

    public static final Criterion H4_3_1 = Criterion.builder()
        .shortDescription("Die Methode <code>testEvaluate</code> wird mit mindestens 5 korrekten Werten aufgerufen.")
        .grader(
            Grader.testAwareBuilder()
                .requirePass(
                    JUnitTestRef.ofMethod(() -> H4_Tests.H_4_3.class.getMethod("test2")))
                .pointsFailedMin()
                .pointsPassedMax()
                .build())
        .build();

    public static final Criterion H4_3_2 = Criterion.builder()
        .shortDescription("Die Methode <code>testEvaluate</code> wird mit mindestens 5 inkorrekten Werten aufgerufen.")
        .grader(Grader.descendingPriority(
            Grader.testAwareBuilder()
                .requirePass(
                    JUnitTestRef.ofMethod(() -> H4_Tests.H_4_3.class.getMethod("test3")))
                .pointsFailedMin()
                .pointsPassedMax()
                .build()))
        .build();

    public static final Criterion H4_3 = Criterion.builder()
        .shortDescription("4.3 | Methode testEvaluate")
        .addChildCriteria(
            H4_3_1,
            H4_3_2)
        .build();


    @Override
    public Rubric getRubric() {
        return Rubric.builder()
            .title("H06 | Strange Things")
            .addChildCriteria(
            H2_1,
            H2_2,
            H3_1,
            H3_2,
            H4_1,
            H4_2,
            H4_3
        ).build();
    }
}
