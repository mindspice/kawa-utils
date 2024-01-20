import java.util.function.Consumer;


public  class TestClass {
    public Consumer<TestClass> consumer;
    public int value = 2;


    public static class Test2 {
        public Consumer<TestClass.Test2> consumer;
        public int value = 2;
    }
}
