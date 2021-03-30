package question.cooperateprint;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreeThreadBaseOnAtomicInteger {

    @Test
    public void test() throws InterruptedException {
        int[] ints = IntStream.range(1, 101).toArray();
        AtomicInteger pointer = new AtomicInteger(0);
        AtomicInteger status = new AtomicInteger(0);
        Thread thread0 = new Thread(() -> {
            while (pointer.get() < 100) {
                if (status.get() == 0) {
                    System.out.println("A: " + ints[pointer.getAndIncrement()]);
                    status.compareAndSet(0, 1);
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            while (pointer.get() < 100) {
                if (status.get() == 1) {
                    System.out.println("B: " + ints[pointer.getAndIncrement()]);
                    status.compareAndSet(1, 2);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (pointer.get() < 100) {
                if (status.get() == 2) {
                    System.out.println("C: " + ints[pointer.getAndIncrement()]);
                    status.compareAndSet(2, 0);
                }
            }
        });
        thread0.start();
        thread1.start();
        thread2.start();

        Thread.sleep(2000);
    }

}
