import gnu.expr.CompiledProc;
import gnu.mapping.Procedure;
import wrappers.functional.consumers.KawaConsumer;

import java.util.function.Consumer;


public  class TestClass {
    public Consumer<TestClass> consumer;
    public int value = 2;
    public String test = "test";
    public Test2 testClass = new Test2();


    public void consumer(TestClass clazz) {
         consumer.accept(clazz);
    }

    public void setConsumer(CompiledProc proc) {
        this.consumer = KawaConsumer.of(proc);
    }

    public static class Test2 {
        public boolean bool = false;
    }

    public static record Test3(int a, boolean b, String c){}
}
