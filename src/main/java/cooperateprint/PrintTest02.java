package cooperateprint;

import java.util.concurrent.Semaphore;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintTest02 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(8, false);
        new Thread(() -> {
            int[] a = new int[]{1, 2, 3, 4, 5};
            int i = 0;
            for (int i1 = 0; i1 < a.length; i1++) {
                i += 2;
                while (semaphore.availablePermits() == i1) {
                    try {
                        semaphore.acquire(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(a[i1]);
                    semaphore.release(i1);
                }
            }

        }).start();

        new Thread(() -> {
            char[] a = new char[]{'a', 'b', 'c', 'd', 'e'};
            int length = a.length;
            int i = 1;
            for (int c = 0; c < a.length; c++) {
                i += 2;
                try {
                    if (semaphore.availablePermits() == length) {
                        semaphore.acquire(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(a[c]);
                semaphore.release(length);
            }
        }).start();
    }

}
