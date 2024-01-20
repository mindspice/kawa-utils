package wrappers;


import java.util.Optional;


public record KawaResult<T>(
        Optional<T> result,
        Optional<Throwable> exception
) {
}
