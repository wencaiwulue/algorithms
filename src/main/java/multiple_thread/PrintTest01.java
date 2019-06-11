package multiple_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fengcaiwen
 * @since 6/10/2019
 */
public class PrintTest01 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(false);
        lock.newCondition();

    }
}
