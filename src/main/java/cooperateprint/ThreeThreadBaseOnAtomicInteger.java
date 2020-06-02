package cooperateprint;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Test {
    private static final int[] ints = IntStream.range(1, 101).toArray();
    private static int i = 0;
    private static final AtomicInteger ai = new AtomicInteger(0);

    public static void test() {
        Thread thread0 = new Thread(() -> {
            while (i < 100) {
                if (ai.get() == 0) {
                    System.out.println("A: " + ints[i++]);
                    ai.compareAndSet(0, 1);
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            while (i < 100) {
                if (ai.get() == 1) {
                    System.out.println("B: " + ints[i++]);
                    ai.compareAndSet(1, 2);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (i < 100) {
                if (ai.get() == 2) {
                    System.out.println("C: " + ints[i++]);
                    ai.compareAndSet(2, 0);
                }
            }
        });
        thread0.start();
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        test();
    }

}
