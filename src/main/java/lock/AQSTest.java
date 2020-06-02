package lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author naison
 * @since 5/6/2020 14:31
 */
public class AQSTest {
    public static void main(String[] args) {
        AbstractQueuedSynchronizer aqs = new Test();
        System.out.println(1 << 16);
//        aqs.acquire(100);
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        rwl.writeLock().lock();
        rwl.writeLock().lock();
        rwl.readLock().lock();

    }

    public static class Test extends AbstractQueuedSynchronizer {
        protected Test() {
            super();
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
    }
}
