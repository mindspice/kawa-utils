package wrappers;

import gnu.expr.Language;

import javax.swing.text.html.Option;
import java.util.Optional;


public record KawaResult<T>(
        Optional<T> result,
        Optional<Throwable> exception
) {
}
