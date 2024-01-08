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
                (list 1 2 3 4 5 7 8 9 10))
                                                        
                """;

        Environment env = scheme.getEnvironment();
        scheme.eval(consumer);

        Procedure generateList = (Procedure) scheme.eval("generate-list");
        Object schemeList = generateList.apply0();

        if (schemeList instanceof LList list) {
            KawaStream.toStream(list, (obj) -> ((IntNum) obj).intValue()).forEach(System.out::println);
        }

    }


}
