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
        private Queue<Runnable> tasks = new ConcurrentLinkedQueue<>();

        private long expire = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5);//minutes

        private final List<Thread> main = new ArrayList<>();

        private volatile AtomicInteger running = new AtomicInteger(0);

        ThreadPool(int n) {
            for (int i = 0; i < n; i++) {
                Thread thread = new Thread(() -> {
                    while (expire > System.currentTimeMillis()) {
                        Runnable run = tasks.poll();
                        if (run != null) {
                            expire += TimeUnit.MINUTES.toMillis(5);
                            running.incrementAndGet();
                            run.run();
                        } else {
                            running.decrementAndGet();
                            LockSupport.park();
                        }
                    }
                });
//                thread.setDaemon(true);
                thread.start();
                main.add(thread);
            }
            System.out.println("init done");
        }

        void getOneAndExec(Runnable run) {
            tasks.add(run);
            for (Thread thread : main) {
                LockSupport.unpark(thread);
            }
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
