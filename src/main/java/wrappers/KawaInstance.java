package wrappers;

import gnu.kawa.io.InPort;
import kawa.standard.Scheme;
import wrappers.functional.streams.KawaStream;

import java.io.Reader;
import java.util.Optional;


public class KawaInstance extends Scheme {



    public <T> KawaResult<T> safeEval(String string) {
        try {
            Object result = eval(string);
            @SuppressWarnings("unchecked")
            T castedResult = (T) result;
            return new KawaResult<>(Optional.of(castedResult), Optional.empty());
        } catch (Throwable e) {
            return new KawaResult<T>(Optional.empty(), Optional.of(e));
        }
    }

    public <T> KawaResult<T> safeEval(Reader in) {
        try {
            Object result = eval(in);
            @SuppressWarnings("unchecked")
            T castedResult = (T) result;
            return new KawaResult<>(Optional.of(castedResult), Optional.empty());
        } catch (Throwable e) {
            return new KawaResult<T>(Optional.empty(), Optional.of(e));
        }
    }

    public <T> KawaResult<T> safeEval(InPort in) {
        try {
            Object result = eval(in);
            @SuppressWarnings("unchecked")
            T castedResult = (T) result;
            return new KawaResult<>(Optional.of(castedResult), Optional.empty());
        } catch (Throwable e) {
            return new KawaResult<T>(Optional.empty(), Optional.of(e));
        }
    }


    public <T> T castEval(String string) throws Throwable {
            Object result = eval(string);
            @SuppressWarnings("unchecked")
            T castedResult = (T) result;
        return castedResult;
    }

    public <T> T castEval(Reader in) throws Throwable {
            Object result = eval(in);
            @SuppressWarnings("unchecked")
            T castedResult = (T) result;
            return castedResult;
    }

    public <T> T castEval(InPort in) throws Throwable {
            Object result = eval(in);
            @SuppressWarnings("unchecked")
            T castedResult = (T) result;
            return castedResult;
    }


}
