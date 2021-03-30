package question.cooperateprint;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintTestBaseOnQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>();

        Thread thread1 = new Thread(() -> {
            int[] ints = new int[]{1, 2, 3, 4, 5};
            for (int i : ints) {
                while (true) {
                    if (queue.peek() == Thread.currentThread()) {
                        System.out.print(i);
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
            char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
            for (char c : chars) {
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
