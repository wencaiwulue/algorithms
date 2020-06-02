package cooperateprint;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintBaseOnWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        String str = "";
        new Thread(new Print(new Object[]{'a', 'b', 'c', 'd', 'e'}, str)).start();
        new Thread(new Print(new Object[]{1, 2, 3, 4, 5}, str)).start();
        Thread.sleep(2000);
    }

    public static class Print implements Runnable {
        private final Object[] a;
        private final String lock;

        Print(Object[] a, String lock) {
            this.a = a;
            this.lock = lock;
        }

        @Override
        public void run() {
            for (Object o : a) {
                synchronized (lock.intern()) {
                    try {
                        System.out.println(o);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.exit(0);
        }
    }

    private static void test2() throws InterruptedException {
        String lock = "";
        AtomicInteger ai = new AtomicInteger(1);
        Thread t = new Thread(() -> {
            while (ai.get() <= 100) {
                synchronized (lock.intern()) {
                    System.out.println("A: " + ai.getAndIncrement());
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t1 = new Thread(() -> {
            while (ai.get() <= 100) {
                synchronized (lock.intern()) {
                    System.out.println("B: " + ai.getAndIncrement());
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
        t1.start();

        Thread.sleep(5000);
    }
}
