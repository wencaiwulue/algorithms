import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.findStatic(X.class, "test", MethodType.methodType(void.class));
        handle.invokeExact();
    }

    private static class X {
        protected static void test() {
            System.out.println("hello");
        }
    }
}
