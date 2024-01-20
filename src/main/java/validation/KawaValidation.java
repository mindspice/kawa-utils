package validation;

import java.util.Optional;


public record KawaValidation(
        boolean isValid,
        Optional<Throwable> error
) {

    public static KawaValidation ofValid() {
        return new KawaValidation(true, Optional.empty());
    }

    public static KawaValidation ofInvalid(Throwable error) {
        return new KawaValidation(false, Optional.of(error));
    }

}
