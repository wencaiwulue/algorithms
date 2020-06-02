package lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author naison
 * @since 5/6/2020 22:44
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Thread t1 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.nanoTime());
        });
        Thread t2 = new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
            }
            System.out.println(System.nanoTime());
        });

        t1.start();
        t2.start();

        Thread.sleep(2000);

        latch.countDown();
    }
}
