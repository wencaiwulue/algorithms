package print;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintTest0 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        new Thread(() -> {
            int[] a = new int[]{1, 2, 3, 4, 5};
            for (int i1 : a) {
                while (true) {
                    boolean b = atomicInteger.compareAndSet(1, 2);
                    if (b) {
                        System.out.println(i1);
                        atomicInteger.compareAndSet(2, 0);
                        break;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            char[] a = new char[]{'a', 'b', 'c', 'd', 'e'};
            for (char c : a) {
                while (true) {
                    boolean b = atomicInteger.compareAndSet(0, 3);
                    if (b) {
                        System.out.println(c);
                        atomicInteger.compareAndSet(3, 1);
                        break;
                    }
                }
            }
        }).start();
    }
}
