package wrappers.functional.predicates;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.predicates.QuadPredicate;


public record KawaQuadPredicate<T, U, V, W>(
        Procedure predicateProcedure
) implements QuadPredicate<T, U, V, W> {

    public static <T, U, V, W> KawaQuadPredicate<T, U, V, W> of(Procedure procedure) {
        return new KawaQuadPredicate<>(procedure);
    }

    @Override
    public boolean test(T t, U u, V v, W w) {
        try {
            return (boolean) predicateProcedure.apply4(t, u, v, w);
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to apply procedure: " + e.getMessage());
        }
    }

}
