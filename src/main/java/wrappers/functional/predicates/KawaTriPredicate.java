package wrappers.functional.predicates;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.predicates.TriPredicate;


public record KawaTriPredicate<T, U, V>(
        Procedure predicateProcedure
) implements TriPredicate<T, U, V> {

    public static <T, U, V> KawaTriPredicate<T, U, V> of(Procedure procedure) {
        return new KawaTriPredicate<>(procedure);
    }

    @Override
    public boolean test(T t, U u, V v) {
        try {
            return (boolean) predicateProcedure.apply3(t, u, v);
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to apply procedure: " + e.getMessage());
        }
    }

}
