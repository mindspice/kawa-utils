package validation;

import gnu.mapping.Procedure;
import kawa.standard.Scheme;
import wrappers.functional.consumers.KawaBiConsumer;
import wrappers.functional.consumers.KawaConsumer;
import wrappers.functional.functions.KawaBiFunction;
import wrappers.functional.functions.KawaFunction;
import wrappers.functional.predicates.KawaBiPredicate;
import wrappers.functional.predicates.KawaPredicate;
import wrappers.functional.suppliers.KawaSupplier;

import java.util.function.*;


public class KawaValidator {

    public static <T> KawaValidation validateConsumer(T obj1, String procName, Scheme kawa) {
        try {
            Consumer<T> consumer = KawaConsumer.of((Procedure) kawa.eval(procName));
            consumer.accept(obj1);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U> KawaValidation validateBiConsumer(T obj1, U obj2, String procName, Scheme kawa) {
        try {
            BiConsumer<T, U> consumer = KawaBiConsumer.of((Procedure) kawa.eval(procName));
            consumer.accept(obj1, obj2);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, R> KawaValidation validateFunction(T obj1, Class<R> rtnClass, String procName, Scheme kawa) {
        try {
            Function<T, R> function = KawaFunction.of((Procedure) kawa.eval(procName));
            rtnClass.cast(function.apply(obj1));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, R> KawaValidation validateBiFunction(T obj1, U obj2, Class<R> rtnClass, String procName, Scheme kawa) {
        try {
            BiFunction<T, U, R> function = KawaBiFunction.of((Procedure) kawa.eval(procName));
            rtnClass.cast(function.apply(obj1, obj2));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T> KawaValidation validatePredicate(T obj1, String procName, Scheme kawa) {
        try {
            Predicate<T> predicate = KawaPredicate.of((Procedure) kawa.eval(procName));
            predicate.test(obj1);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U> KawaValidation validateBiPredicate(T obj1, U obj2, String procName, Scheme kawa) {
        try {
            BiPredicate<T, U> predicate = KawaBiPredicate.of((Procedure) kawa.eval(procName));
            predicate.test(obj1, obj2);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T> KawaValidation validateSupplier(String procName, Class<T> rtnClass, Scheme kawa) {
        try {
            Supplier<T> function = KawaSupplier.of((Procedure) kawa.eval(procName));
            rtnClass.cast(function.get());
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

}
