package validation;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.consumers.QuadConsumer;
import io.mindspice.mindlib.functional.consumers.TriConsumer;
import io.mindspice.mindlib.functional.functions.QuadFunction;
import io.mindspice.mindlib.functional.functions.TriFunction;
import io.mindspice.mindlib.functional.predicates.TriPredicate;
import kawa.standard.Scheme;
import org.mockito.Mockito;
import wrappers.functional.consumers.KawaQuadConsumer;
import wrappers.functional.consumers.KawaTriConsumer;
import wrappers.functional.functions.KawaQuadFunction;
import wrappers.functional.functions.KawaTriFunction;
import wrappers.functional.predicates.KawaQuadPredicate;
import wrappers.functional.predicates.KawaTriPredicate;


public class KawaMLibMockValidator extends KawaMockValidator {

    public static <T, U, V> KawaValidation validateTriConsumer(Class<T> clazz1, Class<U> clazz2,
            Class<V> clazz3, String procName, Scheme kawa) {
        try {
            TriConsumer<T, U, V> consumer = KawaTriConsumer.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            V mock3 = Mockito.mock(clazz3);
            consumer.accept(mock1, mock2, mock3);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, W> KawaValidation validateQuadConsumer(Class<T> clazz1, Class<U> clazz2,
            Class<V> clazz3, Class<W> clazz4, String procName, Scheme kawa) {
        try {
            QuadConsumer<T, U, V, W> consumer = KawaQuadConsumer.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            V mock3 = Mockito.mock(clazz3);
            W mock4 = Mockito.mock(clazz4);
            consumer.accept(mock1, mock2, mock3, mock4);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, R> KawaValidation validateTriFunction(Class<T> clazz1, Class<U> clazz2,
            Class<V> clazz3, Class<R> rtnClazz, String procName, Scheme kawa) {
        try {
            TriFunction<T, U, V, R> function = KawaTriFunction.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            V mock3 = Mockito.mock(clazz3);
            rtnClazz.cast(function.apply(mock1, mock2, mock3));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, W, R> KawaValidation validateQuadFunction(Class<T> clazz1, Class<U> clazz2,
            Class<V> clazz3, Class<W> clazz4, Class<R> rtnClazz, String procName, Scheme kawa) {
        try {
            QuadFunction<T, U, V, W, R> function = KawaQuadFunction.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            V mock3 = Mockito.mock(clazz3);
            W mock4 = Mockito.mock(clazz4);
            rtnClazz.cast(function.apply(mock1, mock2, mock3, mock4));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V> KawaValidation validateTriPredicate(Class<T> clazz1, Class<U> clazz2, Class<V> clazz3,
            String procName, Scheme kawa) {
        try {
            TriPredicate<T, U, V> predicate = KawaTriPredicate.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            V mock3 = Mockito.mock(clazz3);
            predicate.test(mock1, mock2, mock3);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, W> KawaValidation validateQuadPredicate(Class<T> clazz1, Class<U> clazz2, Class<V> clazz3,
            Class<W> clazz4, String procName, Scheme kawa) {
        try {
            KawaQuadPredicate<T, U, V, W> predicate = KawaQuadPredicate.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            V mock3 = Mockito.mock(clazz3);
            W mock4 = Mockito.mock(clazz4);
            predicate.test(mock1, mock2, mock3, mock4);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

}
