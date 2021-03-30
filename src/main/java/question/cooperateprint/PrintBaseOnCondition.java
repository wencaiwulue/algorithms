package question.cooperateprint;

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
            char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
                for (char c : chars) {
                    try {
                        lock.lock();
                        System.out.print(c);
                        condition1.signal();
                        condition0.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            System.exit(0);
        }).start();

        new Thread(() -> {
            int[] ints = new int[]{1, 2, 3, 4, 5};
                for (int value : ints) {
                    try {
                        lock.lock();
                        System.out.print(value);
                        condition0.signal();
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }
}
