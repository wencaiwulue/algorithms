package lock;

//import sun.jvm.hotspot.oops.ObjectHeap;
//import sun.jvm.hotspot.runtime.VM;

import java.util.concurrent.CountDownLatch;

/**
 * @author naison
 * @since 5/3/2020 09:06
 */
public class Synchronized {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        CountDownLatch latch = new CountDownLatch(2);
        Thread t = new Thread(() -> {
            a.test(1000);
            latch.countDown();
        });
        Thread t1 = new Thread(() -> {
            a.test(1000);
            latch.countDown();
        });
        t.start();
        t1.start();
        t.join();
        t1.join();
        latch.await();
//        ObjectHeap objectHeap = VM.getVM().getObjectHeap();


    }


    public static class A {
        public synchronized void test(int i) {
            if (i > 0) {
                test(--i);
            }
        }
    }
}
