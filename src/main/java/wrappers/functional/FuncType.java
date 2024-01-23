package wrappers.functional;

import gnu.mapping.Procedure;
import io.mindspice.mindlib.functional.consumers.QuadConsumer;
import io.mindspice.mindlib.functional.consumers.TriConsumer;
import io.mindspice.mindlib.functional.functions.QuadFunction;
import io.mindspice.mindlib.functional.functions.TriFunction;
import io.mindspice.mindlib.functional.predicates.QuadPredicate;
import io.mindspice.mindlib.functional.predicates.TriPredicate;
import wrappers.functional.consumers.KawaBiConsumer;
import wrappers.functional.consumers.KawaConsumer;
import wrappers.functional.consumers.KawaQuadConsumer;
import wrappers.functional.consumers.KawaTriConsumer;
import wrappers.functional.functions.KawaBiFunction;
import wrappers.functional.functions.KawaFunction;
import wrappers.functional.functions.KawaQuadFunction;
import wrappers.functional.functions.KawaTriFunction;
import wrappers.functional.predicates.KawaBiPredicate;
import wrappers.functional.predicates.KawaPredicate;
import wrappers.functional.predicates.KawaQuadPredicate;
import wrappers.functional.predicates.KawaTriPredicate;
import wrappers.functional.suppliers.KawaSupplier;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.*;


public enum FuncType {
    CONSUMER(KawaConsumer.class, x -> x instanceof Consumer<?>),
    BI_CONSUMER(KawaBiConsumer.class, x -> x instanceof BiConsumer<?, ?>),
    TRI_CONSUMER(KawaTriConsumer.class, x -> x instanceof TriConsumer<?, ?, ?>),
    QUAD_CONSUMER(KawaQuadConsumer.class, x -> x instanceof QuadConsumer<?, ?, ?, ?>),
    FUNCTION(KawaFunction.class, x -> x instanceof Function<?, ?>),
    BI_FUNCTION(KawaBiFunction.class, x -> x instanceof BiFunction<?, ?, ?>),
    TRI_FUNCTION(KawaTriFunction.class, x -> x instanceof TriFunction<?, ?, ?, ?>),
    QUAD_FUNCTION(KawaQuadFunction.class, x -> x instanceof QuadFunction<?, ?, ?, ?, ?>),
    PREDICATE(KawaPredicate.class, x -> x instanceof Predicate<?>),
    BI_PREDICATE(KawaBiPredicate.class, x -> x instanceof BiPredicate<?, ?>),
    TRI_PREDICATE(KawaTriPredicate.class, x -> x instanceof TriPredicate<?, ?, ?>),
    QUAD_PREDICATE(KawaQuadPredicate.class, x -> x instanceof QuadPredicate<?, ?, ?, ?>),
    SUPPLIER(KawaSupplier.class, x -> x instanceof Supplier<?>);

    public final Class<?> clazz;
    public final Predicate<Object> validator;

    FuncType(Class<?> clazz, Predicate<Object> validator) {
        this.clazz = clazz;
        this.validator = validator;
    }

    public static FuncType fromString(String string) {
        try {
            return FuncType.valueOf(string.toUpperCase().replace("-", "_"));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean validate(Object dataObj) {
        return validator.test(dataObj);
    }

    public <T> T castOrNull(Object dataObj) {
        if (validate(dataObj)) {
            @SuppressWarnings("unchecked")
            T casted = (T) dataObj;
            return casted;
        }
        return null;
    }

    public FuncRef funcRefFromProcedure(Procedure procedure) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(this, clazz.getConstructor(Procedure.class).newInstance(procedure));
    }

    public FuncRef funcRefFromProcedure(Object procedure) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(this, clazz.getConstructor(Procedure.class).newInstance((Procedure) procedure));
    }

    public FuncRef funcRefFromProcedure(Procedure procedure, List<Class<?>> argTypes) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(this, clazz.getConstructor(Procedure.class).newInstance(procedure), argTypes);
    }

    public FuncRef funcRefFromProcedure(Object procedure, List<Class<?>> argTypes) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(this, clazz.getConstructor(Procedure.class).newInstance((Procedure) procedure), argTypes);
    }

    public static FuncRef funcRefFromProcedure(FuncType type, Procedure procedure) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(type, type.clazz.getConstructor(Procedure.class).newInstance(procedure));
    }

    public static FuncRef funcRefFromProcedure(FuncType type, Object procedure) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(type, type.clazz.getConstructor(Procedure.class).newInstance((Procedure) procedure));
    }

    public static FuncRef funcRefFromProcedure(FuncType type, Procedure procedure, List<Class<?>> argTypes) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(type, type.clazz.getConstructor(Procedure.class).newInstance(procedure), argTypes);
    }

    public static FuncRef funcRefFromProcedure(FuncType type, Object procedure, List<Class<?>> argTypes) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        return FuncRef.of(type, type.clazz.getConstructor(Procedure.class).newInstance((Procedure) procedure), argTypes);
    }
}
