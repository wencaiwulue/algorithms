package question.cooperateprint;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintTestBaseOnAtomicInteger {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        new Thread(() -> {
            int[] ints = new int[]{1, 2, 3, 4, 5};
            for (int i : ints) {
                while (true) {
                    if (atomicInteger.compareAndSet(1, Integer.MIN_VALUE)) {
                        System.out.print(i);
                        atomicInteger.compareAndSet(Integer.MIN_VALUE, 0);
                        break;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
            for (char c : chars) {
                while (true) {
                    if (atomicInteger.compareAndSet(0, Integer.MIN_VALUE)) {
                        System.out.print(c);
                        atomicInteger.compareAndSet(Integer.MIN_VALUE, 1);
                        break;
                    }
                }
            }
        }).start();
    }
}
