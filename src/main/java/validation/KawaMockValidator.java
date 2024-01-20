package validation;

import gnu.mapping.Procedure;

import kawa.standard.Scheme;
import org.mockito.Mockito;
import wrappers.functional.consumers.KawaBiConsumer;
import wrappers.functional.consumers.KawaConsumer;
import wrappers.functional.functions.KawaBiFunction;
import wrappers.functional.functions.KawaFunction;
import wrappers.functional.predicates.KawaBiPredicate;
import wrappers.functional.predicates.KawaPredicate;
import wrappers.functional.suppliers.KawaSupplier;

import java.util.function.*;


public class KawaMockValidator {

    public static <T> KawaValidation validateConsumer(Class<T> clazz, String procName, Scheme kawa) {
        try {
            Consumer<T> consumer = KawaConsumer.of((Procedure) kawa.eval(procName));
            T mock = Mockito.mock(clazz);
            consumer.accept(mock);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U> KawaValidation validateBiConsumer(Class<T> clazz1, Class<U> clazz2,
            String procName, Scheme kawa) {
        try {
            BiConsumer<T, U> consumer = KawaBiConsumer.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            consumer.accept(mock1, mock2);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, R> KawaValidation validateFunction(Class<T> clazz, Class<R> rtnClazz,
            String procName, Scheme kawa) {
        try {
            Function<T, R> function = KawaFunction.of((Procedure) kawa.eval(procName));
            T mock = Mockito.mock(clazz);
            rtnClazz.cast(function.apply(mock));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U, R> KawaValidation validateBiFunction(Class<T> clazz1, Class<U> clazz2, Class<R> rtnClazz,
            String procName, Scheme kawa) {
        try {
            BiFunction<T, U, R> function = KawaBiFunction.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            rtnClazz.cast(function.apply(mock1, mock2));
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T> KawaValidation validatePredicate(Class<T> clazz, String procName, Scheme kawa) {
        try {
            Predicate<T> predicate = KawaPredicate.of((Procedure) kawa.eval(procName));
            T mock = Mockito.mock(clazz);
            predicate.test(mock);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T, U> KawaValidation validateBiPredicate(Class<T> clazz1, Class<U> clazz2,
            String procName, Scheme kawa) {
        try {
            BiPredicate<T, U> predicate = KawaBiPredicate.of((Procedure) kawa.eval(procName));
            T mock1 = Mockito.mock(clazz1);
            U mock2 = Mockito.mock(clazz2);
            predicate.test(mock1, mock2);
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

    public static <T> KawaValidation validateSupplier(Class<T> rtnClass, String procName, Scheme kawa) {
        try {
            Supplier<T> function = KawaSupplier.of((Procedure) kawa.eval(procName));
            rtnClass.cast(function.get());
        } catch (Throwable e) {
            return KawaValidation.ofInvalid(e);
        }
        return KawaValidation.ofValid();
    }

}
