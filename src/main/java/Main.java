import gnu.expr.ModuleBody;
import gnu.kawa.functions.LProcess;
import gnu.lists.U8Vector;
import kawa.standard.SchemeCompilation;
import wrappers.KawaInstance;
import wrappers.functional.streams.KawaDoubleStream;
import gnu.expr.Language;

import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import kawa.standard.Scheme;

import java.util.List;


public class Main {

    public static void main(String[] args) throws Throwable {
        KawaInstance kawa = new KawaInstance();



        String consumer = """
                (define (generate-list)
                (list 1.2 2.2 3.342342 4.42342342342 5.342 7.32 8.3423423425123123123123123235252 9.12 10.3423432))
                                                        
                """;

        kawa.safeEval(consumer);
        double[][] d1 = new double[100_000][10];
        double[][] d2 = new double[100_000][10];

        var t = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            var result = kawa.<Procedure>castEval("generate-list");
            Object schemeList = result.apply0();

            d1[i] =  KawaDoubleStream.toStream(schemeList).toArray();
        }

        System.out.println(System.nanoTime() -t);

        t = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            var result = (Procedure) kawa.eval("generate-list");
            Object schemeList =  result.apply0();
            d2[i] = KawaDoubleStream.toStream(schemeList).toArray();
        }
        System.out.println(System.nanoTime() - t);

        System.out.println(d1);
        System.out.println(d2);




//
//        var result = kawa.<Procedure>safeEval("generate-list");
//        Object schemeList = result.result().orElseThrow().apply0();
//
//            KawaDoubleStream.toStream(schemeList).forEach(System.out::println);


    }


}
