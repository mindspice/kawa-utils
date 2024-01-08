package functionalwrappers.functions;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.functions.QuadFunction;
import io.mindspice.mindlib.functional.functions.TriFunction;


public record KawaQuadFunction<T, U, V, W, R>(
        Procedure functionProcedure
) implements QuadFunction<T, U, V, W, R> {

    public static <T, U, V, W, R> KawaQuadFunction<T, U, V, W, R> of(Procedure procedure) {
        return new KawaQuadFunction<>(procedure);
    }

    @Override
    public R apply(T t, U u, V v, W w) {
        try {
            @SuppressWarnings("unchecked")
            R result = (R) functionProcedure.apply4(t, u, v, w);
            return result;
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to apply procedure: " + e.getMessage());
        }
    }
}
