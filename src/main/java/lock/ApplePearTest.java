package lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naison
 * @since 4/29/2020 16:05
 */
public class ApplePearTest {
    static class A {
    }
    static class B extends A {
    }

    public static void main(String[] args) {
        List<? super A> list = new ArrayList<>();
        list.add(new A());
        list.add(new B());
    }

    public static void test(List<? super A> list) {
        for (Object o : list) {
            A a = (A) o;
        }
    }
}
