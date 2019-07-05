package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author fengcaiwen
 * @since 7/4/2019
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        readLock.lock();
        writeLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        writeLock.lock();
        readLock.unlock();
        writeLock.unlock();

    }
}
