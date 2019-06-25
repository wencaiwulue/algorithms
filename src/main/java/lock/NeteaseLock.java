package lock;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class NeteaseLock implements Lock {

    private AtomicReference<Thread> owner = new AtomicReference<>();
    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

    @Override
    public void lock() {
        while (!owner.compareAndSet(null, Thread.currentThread())) {
            waiters.add(Thread.currentThread());
            LockSupport.park();
        }
        waiters.remove(Thread.currentThread());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (owner.compareAndSet(Thread.currentThread(), null)) {
            Thread thread;
            while ((thread = waiters.poll()) != null) {
                LockSupport.unpark(thread);
            }
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static class Counter {
        private int p;
        private Lock lock;

        public Counter(int p, Lock lock) {
            this.p = p;
            this.lock = lock;
        }

        public void add() {
            try {
                lock.lock();
                // not a atomic operation, not thread safe
                p++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NeteaseLock lock = new NeteaseLock();
        Counter counter = new Counter(0, lock);
        int round = 2000;
        CountDownLatch latch = new CountDownLatch(round);
        for (int i = 0; i < round; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++)
                        counter.add();
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        // let all thread finish work
        latch.await();
        System.out.println(counter.p);
    }
}
