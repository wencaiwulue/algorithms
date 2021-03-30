package multiplethread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
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
        private final Queue<Runnable> tasks = new ConcurrentLinkedQueue<>();
        private final Queue<FutureTest> futureTasks = new ConcurrentLinkedQueue<>();

        // after expire time free, thread pool will destroy itself
        private long expire = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5);//minutes

        // core thread
        private final List<Thread> threads = new ArrayList<>();

        // running thread amount
        private final AtomicInteger running = new AtomicInteger(0);

        // core thread size
        private final int n;

        ThreadPool(int n) {
            this.n = n;
            createThreadDynamic();
        }

        void exec(Runnable run) {
            tasks.add(run);
            if (threads.size() < n)
                createThreadDynamic();

            if (running.get() != n)
                for (Thread thread : threads)
                    if (Thread.State.BLOCKED.equals(thread.getState()))
                        LockSupport.unpark(thread);

        }

        public FutureTest submit(Callable<?> callable) {
            FutureTest futureTest = new FutureTest(callable);
            futureTasks.add(futureTest);
            if (threads.size() < n)
                createThreadDynamic();

            if (running.get() != n)
                for (Thread thread : threads)
                    if (Thread.State.BLOCKED.equals(thread.getState()))
                        LockSupport.unpark(thread);

            return futureTest;
        }

        private void createThreadDynamic() {
            Thread thread = new Thread(() -> {
                while (expire > System.currentTimeMillis()) {
                    Runnable runnable = tasks.poll();
                    FutureTest test;
                    if (runnable != null) {
                        expire += TimeUnit.MINUTES.toMillis(5);
                        running.incrementAndGet();
                        runnable.run();
                    } else if ((test = futureTasks.poll()) != null) {
                        try {
//                            Object result = check196.get();
                            test.setResult("null");
                        } catch (Exception e) {
                            e.printStackTrace();
                            test.setResult("null");
                        }
                    } else {
                        System.out.println("running amount： " + running.get());
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
            threadPool.exec(runnable);
        }

        while (true)
            if (threadPool.isFree()) break;

        long end = System.nanoTime();

        System.out.println(TimeUnit.NANOSECONDS.toSeconds(end - start) + " ms");

    }
}
