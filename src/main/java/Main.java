import functionalwrappers.consumers.KawaBiConsumer;
import functionalwrappers.consumers.KawaQuadConsumer;
import functionalwrappers.consumers.KawaTriConsumer;
import functionalwrappers.functions.KawaFunction;
import functionalwrappers.predicates.KawaPredicate;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import kawa.standard.Scheme;

import java.util.function.Predicate;


public class Main {

    public static void main(String[] args) throws Throwable {
        Language scheme = Scheme.getInstance();

        String consumer = """
                (define (predicate obj)
                    (* (invoke obj 'weight) 2))
                """;

        Environment env = scheme.getEnvironment();

        var tr = new TestRecord(2);
        scheme.eval(consumer);

        Procedure p = (Procedure) scheme.eval("predicate");
        KawaFunction<TestRecord, Integer> pred = KawaFunction.of(p);

        System.out.println(pred.apply(tr));
    }

    public record TestRecord(
            int weight
    ){}



}
