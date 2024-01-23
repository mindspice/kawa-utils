import gnu.bytecode.ObjectType;
import gnu.expr.Language;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.servlet.KawaHttpHandler;
import gnu.lists.FString;
import gnu.mapping.*;
import gnu.text.SyntaxException;
import io.mindspice.mindlib.data.geometry.IConstVector2;
import io.mindspice.mindlib.data.geometry.IVector2;
import io.mindspice.mindlib.data.tuples.Pair;
import kawa.standard.Scheme;
import org.mockito.Mockito;
import validation.KawaMockValidator;
import validation.KawaValidator;
import wrappers.KawaInstance;
import wrappers.KawaResult;
import wrappers.functional.FuncRef;
import wrappers.functional.FuncType;
import wrappers.functional.consumers.KawaBiConsumer;
import wrappers.functional.consumers.KawaConsumer;

import wrappers.functional.functions.KawaBiFunction;
import wrappers.functional.functions.KawaFunction;
import wrappers.functional.predicates.KawaBiPredicate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.*;


public class Main {
    // Test procedure first

    public static <T> Pair<Boolean, Throwable> testConsumer(Class<T> clazz, String procName, KawaInstance kawa) {
        try {
            Consumer<T> consumer = KawaConsumer.of((Procedure) kawa.eval(procName));
            var mock = Mockito.mock(clazz);
            consumer.accept(mock);
        } catch (Throwable e) {
            return Pair.of(Boolean.FALSE, e);
        }
        return Pair.of(Boolean.TRUE, null);
    }

    public TestClass testClass;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static String s = "none";

    public static Consumer<String> setString() {
        return (String str) -> s = str;
    }

    public static void main(String[] args) throws Throwable {
        try {
            // Explicitly set the language to Scheme
            KawaInstance kawa = new KawaInstance();

            // Exposing the TestClass instance to Kawa
            TestClass testInstance = new TestClass();
            kawa.defineObject("test-state", testInstance);
            kawa.define("main", Main.class);
            ;

            var res = kawa.loadSchemeFile(new File("/home/mindspice/code/Java/kawa-utils/src/main/resources/test.scm"));
            System.out.println(res);
            System.out.println(kawa.objectDefinitions());
            System.out.println(kawa.schemeDefinitions());

            kawa.eval("(define (func x) (* x x))");
            FuncRef ref = FuncType.FUNCTION.funcRefFromProcedure(kawa.eval("func"));
            Function<Integer, Integer> con = ref.castedFunction();
            if (con == null) {
                System.out.println("con is null");
            } else {
                System.out.println("output: " + con.apply(100));
            }

            // Setting up the input loop
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.println("Enter Kawa Scheme expressions (type 'exit' to quit):");

            while (true) {
                System.out.println("string is: " + s);
                System.out.print("> ");
                line = br.readLine();
                if (line.equals("exit")) { break; }

                try {
                    KawaResult<Object> result = kawa.safeEval(line);
                    if (result.result().isPresent()) {
                        System.out.println(result.result().get());
                    }
                    if (result.exception().isPresent()) {
                        System.out.println(ANSI_RED + result.exception().get().getMessage());
                        System.out.print(ANSI_RESET);
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

//        KawaInstance kawaTest = new KawaInstance();
//        String consumer = """
//               ;;(import io.mindspice.mindlib.data.geometry.IConstVector2 )
//                 (define (getVec x ::int  y ::int)
//                   (io.mindspice.mindlib.data.geometry.IConstVector2 x y))
//                """;
//
//        kawa.eval(consumer);
//
//        var valid = KawaValidator.validateBiFunction(1, 10, IConstVector2.class, "getVec", kawa);
//        System.out.println(valid);
//
//        //kawa.eval(consumer);
//
//        BiFunction<Integer, Integer, IVector2> vec = KawaBiFunction.of((Procedure) kawa.eval("getVec"));
//        System.out.println(vec.apply(10, 12) instanceof IVector2);

    }
}
