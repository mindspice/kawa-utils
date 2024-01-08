package functionalwrappers.functions;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.functions.TriFunction;

import java.util.function.BiFunction;


public record KawaTriFunction<T, U, V, R>(
        Procedure functionProcedure
) implements TriFunction<T, U, V, R> {

    public static <T, U, V, R> KawaTriFunction<T, U, V, R> of(Procedure procedure) {
        return new KawaTriFunction<>(procedure);
    }

    @Override
    public R apply(T t, U u, V v) {
        try {
            @SuppressWarnings("unchecked")
            R result = (R) functionProcedure.apply3(t, u, v);
            return result;
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to apply procedure: " + e.getMessage());
        }
    }
}
