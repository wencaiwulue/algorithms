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

    static class O {
        int i = 0;
        Lock lock = new NeteaseLock();

        void add() {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        O o = new O();
        int round = 20000;
        CountDownLatch countDown = new CountDownLatch(round);
        for (int i = 0; i < round; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1; j++) {
                        o.add();
                    }
                } finally {
                    countDown.countDown();
                }
            }).start();
        }
        countDown.await();
        System.out.println(o.i);
    }
}
