package cooperateprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintBaseOnCondition {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition0 = lock.newCondition();
        Condition condition1 = lock.newCondition();
        new Thread(() -> {
            char[] a = new char[]{'a', 'b', 'c', 'd', 'e'};
            while (true) {
                for (char c : a) {
                    try {
                        lock.lock();
                        System.out.print(c);
                        condition1.signal();
                        condition0.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            System.exit(0);
        }).start();

        new Thread(() -> {
            int[] a = new int[]{1, 2, 3, 4, 5};
            while (true) {
                for (int value : a) {
                    try {
                        lock.lock();
                        System.out.print(value);
                        condition0.signal();
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }).start();

    }
}
