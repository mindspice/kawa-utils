package wrappers.functional.consumers;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.consumers.QuadConsumer;


public record KawaQuadConsumer<T, U, V, W>(
        Procedure consumerProcedure
) implements QuadConsumer<T, U, V, W> {

    public static <T, U, V, W> KawaQuadConsumer<T, U, V, W> of(Procedure procedure) {
        return new KawaQuadConsumer<>(procedure);
    }

    @Override
    public void accept(T t, U u, V v, W w) {
        try {
            consumerProcedure.apply4(t, u, v, w);
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to apply procedure: " + e.getMessage());
        }
    }
}
