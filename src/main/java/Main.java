import io.mindspice.mindlib.data.tuples.Pair;
import org.mockito.Mockito;
import validation.KawaValidator;
import wrappers.KawaInstance;
import wrappers.functional.consumers.KawaConsumer;

import gnu.mapping.Procedure;
import wrappers.functional.functions.KawaFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class Main {
    // Test procedure first
    public static <T> Pair<Boolean, Throwable> testConsumer(Class<T> clazz, String procName, KawaInstance kawa) {
        try {
            Consumer<T> consumer = KawaConsumer.of((Procedure) kawa.eval(procName));
            var mock = Mockito.mock(clazz);
            consumer.accept(mock);
        } catch (Throwable e) {
            return Pair.of(Boolean.FALSE, e);
        }
        return Pair.of(Boolean.TRUE, null);
    }

    public static void main(String[] args) throws Throwable {
        KawaInstance kawa = new KawaInstance();
        KawaInstance kawaTest = new KawaInstance();

        String consumer = """
                 (define (inc cl ::TestClass:Test2)
                   (* cl:value cl:value))
                """;

        kawa.eval(consumer);
        var tc = new TestClass.Test2();

        var valid = KawaValidator.validateFunction(tc, String.class, "inc", kawa);
        System.out.println(valid);

        //kawa.eval(consumer);


        Function<TestClass.Test2, Integer> func = KawaFunction.of((Procedure) kawa.eval("inc"));
        System.out.println(func.apply(tc));

    }
}
