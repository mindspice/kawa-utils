package wrappers;

import gnu.kawa.io.InPort;
import kawa.standard.Scheme;
import wrappers.functional.FuncRef;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;


public class KawaInstance extends Scheme {
    private List<String> definitions = new ArrayList<>();
    private Map<String, FuncRef> functionMap = new HashMap<>();

    public void defineObject(String defName, Object object) {
        definitions.add(defName);
        define(defName, object);
    }

    public List<String> objectDefinitions() {
        if (definitions == null) { return List.of(); }
        return Collections.unmodifiableList(definitions);
    }

    public List<String> schemeDefinitions() {
        List<String> defs = new ArrayList<>();
        getEnvironment().enumerateLocations().forEachRemaining(d ->
                defs.add(d.getKey().toString() + ":" + d.getValue())
        );
        return defs;
    }

    public KawaResult<?> loadSchemeFile(File file) {
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            return safeEval(reader);
        } catch (Exception e) {
            return new KawaResult<>(null, Optional.of(e));
        }
    }

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
