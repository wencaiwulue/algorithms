package cooperateprint;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Test {
    private static int[] ints = IntStream.range(0, 100).toArray();
    private static int i = 0;
    private static AtomicInteger ai = new AtomicInteger(0);

    public static void test() {
        Thread thread0 = new Thread(() -> {
            while (true) {
                if (ai.get() == 0) {
                    System.out.println(ints[++i] + Thread.currentThread().getName());
                    ai.compareAndSet(0, 1);
                }
            }
        }, "0");
        Thread thread1 = new Thread(() -> {
            while (true) {
                if (ai.get() == 1) {
                    System.out.println(ints[++i] + Thread.currentThread().getName());
                    ai.compareAndSet(1, 2);
                }
            }
        }, "1");
        Thread thread2 = new Thread(() -> {
            while (true) {
                if (ai.get() == 2) {
                    System.out.println(ints[++i] + Thread.currentThread().getName());
                    ai.compareAndSet(2, 0);
                }
            }
        }, "2");
        thread0.start();
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        test();
    }

}
