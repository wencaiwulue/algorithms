package multiple_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author fengcaiwen
 * @since 6/19/2019 21:25
 */
public class ThreadPoolTest01 {

    public static class ThreadPool {

        // task queue
        private Queue<Runnable> tasks = new ConcurrentLinkedQueue<>();

        // after expire time free, thread pool will destroy itself
        private long expire = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5);//minutes

        // core thread
        private final List<Thread> threads = new ArrayList<>();

        // running thread amount
        private volatile AtomicInteger running = new AtomicInteger(0);

        // core thread size
        private int n;

        ThreadPool(int n) {
            this.n = n;
            createThreadDynamic();
        }

        void getOneAndExec(Runnable run) {
            tasks.add(run);
            if (threads.size() < n) {
                createThreadDynamic();
            }
            if (running.get() != n) {
                for (Thread thread : threads) {
                    if (Thread.State.BLOCKED.equals(thread.getState())) {
                        LockSupport.unpark(thread);
                    }
                }
            }
        }

        private void createThreadDynamic() {
            Thread thread = new Thread(() -> {
                while (expire > System.currentTimeMillis()) {
                    Runnable runnable = tasks.poll();
                    if (runnable != null) {
                        expire += TimeUnit.MINUTES.toMillis(5);
                        running.incrementAndGet();
                        runnable.run();
                    } else {
                        running.decrementAndGet();
                        LockSupport.park();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        boolean isFree() {
            return running.get() == 0;
        }
    }

    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(10);
        Runnable runnable = () -> {
            try {
                System.out.println("ok");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        long start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            threadPool.getOneAndExec(runnable);
        }
        while (!threadPool.isFree()) {

        }
        long end = System.nanoTime();

        System.out.println(TimeUnit.NANOSECONDS.toSeconds(end - start) + " ms");

    }
}
