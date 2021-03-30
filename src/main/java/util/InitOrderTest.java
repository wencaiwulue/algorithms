package util;

/**
 * @author naison
 * @since 5/2/2020 10:16
 */
public class InitOrderTest {
    public static void main(String[] args) {
        new B();
    }

    static class A {
        String string = "s";

        static {
            System.out.println("father static area");
        }

        static String s = "str";

        public A() {
            System.out.println("father constructor");
        }
    }

    static class B extends A {
        static String ss = "a";
        String sss = "b";

        static {
            System.out.println("sub static area");
        }

        public B() {
            System.out.println("sub constructor");
        }
    }
}
