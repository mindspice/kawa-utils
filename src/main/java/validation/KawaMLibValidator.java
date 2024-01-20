package validation;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.consumers.QuadConsumer;
import io.mindspice.mindlib.functional.consumers.TriConsumer;
import io.mindspice.mindlib.functional.functions.QuadFunction;
import io.mindspice.mindlib.functional.functions.TriFunction;
import io.mindspice.mindlib.functional.predicates.TriPredicate;
import kawa.standard.Scheme;
import wrappers.functional.consumers.KawaQuadConsumer;
import wrappers.functional.consumers.KawaTriConsumer;
import wrappers.functional.functions.KawaQuadFunction;
import wrappers.functional.functions.KawaTriFunction;
import wrappers.functional.predicates.KawaQuadPredicate;
import wrappers.functional.predicates.KawaTriPredicate;


public class KawaMLibValidator extends KawaMockValidator {

    public static <T, U, V> KawaValidation validateTriConsumer(T obj1, U obj2, V obj3, String procName, Scheme kawa) {
        try {
            TriConsumer<T, U, V> consumer = KawaTriConsumer.of((Procedure) kawa.eval(procName));
            consumer.accept(obj1, obj2, obj3);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, W> KawaValidation validateQuadConsumer(T obj1, U obj2, V obj3, W obj4,
            String procName, Scheme kawa) {
        try {
            QuadConsumer<T, U, V, W> consumer = KawaQuadConsumer.of((Procedure) kawa.eval(procName));

            consumer.accept(obj1, obj2, obj3, obj4);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, R> KawaValidation validateTriFunction(T obj1, U obj2, V obj3, Class<R> rtnClass, String procName, Scheme kawa) {
        try {
            TriFunction<T, U, V, R> function = KawaTriFunction.of((Procedure) kawa.eval(procName));
            rtnClass.cast(function.apply(obj1, obj2, obj3));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, W, R> KawaValidation validateQuadFunction(T obj1, U obj2, V obj3, W obj4,
            Class<R> rtnClass, String procName, Scheme kawa) {
        try {
            QuadFunction<T, U, V, W, R> function = KawaQuadFunction.of((Procedure) kawa.eval(procName));
            rtnClass.cast(function.apply(obj1, obj2, obj3, obj4));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V> KawaValidation validateTriPredicate(T obj1, U obj2, V obj3, String procName, Scheme kawa) {
        try {
            TriPredicate<T, U, V> predicate = KawaTriPredicate.of((Procedure) kawa.eval(procName));
            predicate.test(obj1, obj2, obj3);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, V, W> KawaValidation validateQuadPredicate(T obj1, U obj2, V obj3, W obj4,
            String procName, Scheme kawa) {
        try {
            KawaQuadPredicate<T, U, V, W> predicate = KawaQuadPredicate.of((Procedure) kawa.eval(procName));
            predicate.test(obj1, obj2, obj3, obj4);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

}
