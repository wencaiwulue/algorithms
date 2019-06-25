package cooperateprint;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintTest03 {
    public static void main(String[] args) {
        LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>();

        Thread thread1 = new Thread(() -> {
            int[] a = new int[]{1, 2, 3, 4, 5};
            for (int i1 : a) {
                while (true) {
                    if (queue.peek() == Thread.currentThread()) {
                        System.out.print(i1);
                        try {
                            queue.put(Thread.currentThread());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        queue.poll();
                        break;
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            char[] a = new char[]{'a', 'b', 'c', 'd', 'e'};
            for (char c : a) {
                while (true) {
                    if (queue.peek() == Thread.currentThread()) {
                        try {
                            System.out.print(c);
                            queue.put(Thread.currentThread());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        queue.poll();
                        break;
                    }

                }
            }
        });
        queue.add(thread2);
        queue.add(thread1);

        thread2.start();
        thread1.start();

    }
}
