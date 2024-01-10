package wrappers.functional.consumers;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.consumers.TriConsumer;


public record KawaTriConsumer<T, U, V>(
        Procedure consumerProcedure
) implements TriConsumer<T, U, V> {

    public static <T, U, V> KawaTriConsumer<T, U, V> of(Procedure procedure) {
        return new KawaTriConsumer<>(procedure);
    }

    @Override
    public void accept(T t, U u, V v) {
        try {
            consumerProcedure.apply3(t, u, v);
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to apply procedure: " + e.getMessage());
        }
    }
}
