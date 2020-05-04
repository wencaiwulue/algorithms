package lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author naison
 * @since 4/29/2020 15:59
 */
public class WaitTest {
    static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) {

        A a = new A();
        try {
            synchronized (ai) {
                ai.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.start();

    }

    public static class A extends Thread {
        @Override
        public void run() {
//            try {
//                ai.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (ai) {
                ai.notify();
                ai.lazySet(1);
                System.out.println(ai.get());
            }
        }
    }
}
