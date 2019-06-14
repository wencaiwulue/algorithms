package multiple_thread;

import java.util.concurrent.Semaphore;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintTest02 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(8, false);
        new Thread(() -> {
            int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
            for (int i1 = 0; i1 < a.length; i1++) {
                while (semaphore.availablePermits() == i1) {
                    try {
                        semaphore.acquire(i1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(a[i1]);
                    semaphore.release(i1);
                }
            }

        }).start();

        new Thread(() -> {
            char[] a = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
            int length = a.length;
            for (int c = 0; c < a.length; c++) {
                try {
                    if (semaphore.availablePermits() == length){
                        semaphore.acquire(length--);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(a[c]);
                semaphore.release(length);
            }
        }).start();
//        Thread thread = new Thread(new PrintA(semaphore));
//        Thread thread1 = new Thread(new PrintB(semaphore));
//        thread.start();
//        thread1.start();
//        Consumer consumer = ->{}
    }

}
