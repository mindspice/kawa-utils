import functionalwrappers.consumers.KawaBiConsumer;
import functionalwrappers.consumers.KawaQuadConsumer;
import functionalwrappers.consumers.KawaTriConsumer;
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
                    (if (eq? (modulo (invoke obj 'weight) 2) 0) #t #f))
                """;

        Environment env = scheme.getEnvironment();

        var tr = new TestRecord(2);
        scheme.eval(consumer);

        Procedure p = (Procedure) scheme.eval("predicate");
        Predicate<TestRecord> pred = KawaPredicate.of(p);

        System.out.println(pred.test(tr));
    }

    public record TestRecord(
            int weight
    ){}



}
