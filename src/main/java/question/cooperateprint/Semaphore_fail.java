package question.cooperateprint;

import java.util.concurrent.Semaphore;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class Semaphore_fail {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, true);
        new Thread(() -> {
            int[] ints = new int[]{1, 2, 3, 4, 5};

            for (int i = 0; i < ints.length; i++) {
//                while (true) {
                semaphore.acquireUninterruptibly(3);
                System.out.print(ints[i]);
                semaphore.release(2);
//                break;
//                }
            }
        }).start();

        new Thread(() -> {
            char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
            for (int c = 0; c < chars.length; c++) {
//                while (true) {
                semaphore.acquireUninterruptibly(2);
                System.out.print(chars[c]);
                semaphore.release(2);
//                break;
//                }
            }
        }).start();
    }

}
