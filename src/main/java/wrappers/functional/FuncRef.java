package wrappers.functional;

import java.util.List;


public record FuncRef(
        FuncType type,
        Object function,
        List<Class<?>> argTypes
) {
    public FuncRef {
        if (!type.validate(function)) {
            throw new IllegalStateException("Function failed to validate as function type");
        }
    }

    public static FuncRef of(FuncType type, Object function) {
        return new FuncRef(type, function, List.of());
    }

    public static FuncRef of(FuncType type, Object function, List<Class<?>> argTypes) {
        return new FuncRef(type, function, argTypes);
    }

    public <T> T castedFunction() {
        return type.castOrNull(function);
    }

    public FuncRef withArgs(List<Class<?>> argTypes) {
        return new FuncRef(type, function, argTypes);
    }
}
