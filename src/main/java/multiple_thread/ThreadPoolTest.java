package multiple_thread;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author fengcaiwen
 * @since 6/19/2019 21:25
 */
@SuppressWarnings("all")
public class ThreadPoolTest {


    public static class ThreadTest {

        private Runnable runnable;

        public Runnable getRunnable() {
            return runnable;
        }

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        public void start() {
            runnable.run();
        }
    }

    public static class ThreadPool {

        Queue<ThreadTest> readyQueue = new ConcurrentLinkedQueue<>();
        Queue<ThreadTest> running = new ConcurrentLinkedQueue<>();
        Queue<Runnable> needToRun = new ConcurrentLinkedQueue<>();

        final Thread main;

        public ThreadPool(int n) {
            for (int i = 0; i < n; i++) {
                readyQueue.add(new ThreadTest());
            }
            main = new Thread(() -> {
                while (true) {
                    ThreadTest peek = running.poll();
                    if (peek != null) {
                        peek.start();
                        // reuse
                        peek.setRunnable(null);
                        readyQueue.add(peek);
                    } else {
                        // todo
                        Runnable poll = needToRun.poll();
                    }
                }
            });
            main.setDaemon(true);
            main.start();
            System.out.println("init done");
        }

        public void getOneAndExec(Runnable run) {
            ThreadTest thread = readyQueue.poll();
            // means have no free thread
            if (thread != null) {
                thread.setRunnable(run);
                running.add(thread);
            } else {
                needToRun.add(run);
                System.out.println("no enough thread");
            }
        }
        public int free() {
            return readyQueue.size();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ThreadPool threadPool = new ThreadPool(10);
        Runnable runnable = () -> {
            try {
                System.out.println("ok");
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };


        threadPool.getOneAndExec(runnable);
        threadPool.getOneAndExec(runnable);
        threadPool.getOneAndExec(runnable);
        Thread.sleep(5 * 1000);

//        Thread thread = new Thread(() -> {
//            while (true) {
//                System.out.println("test");
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
//
//        Thread.sleep(5000);

    }
}
