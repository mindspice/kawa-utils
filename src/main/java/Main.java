import functionalwrappers.streams.KawaDoubleStream;
import functionalwrappers.streams.KawaIntStream;
import functionalwrappers.streams.KawaLongStream;
import functionalwrappers.streams.KawaStream;
import gnu.expr.Language;

import gnu.lists.LList;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import kawa.standard.Scheme;


public class Main {

    public static void main(String[] args) throws Throwable {
        Language scheme = Scheme.getInstance();

        String consumer = """
                (define (generate-list)
                (list 1.2 2.2 3.342342 4.42342342342 5.342 7.32 8.3423423425123123123123123235252 9.12 10.3423432))
                                                        
                """;

        Environment env = scheme.getEnvironment();
        scheme.eval(consumer);

        Procedure generateList = (Procedure) scheme.eval("generate-list");
        Object schemeList = generateList.apply0();

            KawaDoubleStream.toStream(schemeList).forEach(System.out::println);


    }


}
