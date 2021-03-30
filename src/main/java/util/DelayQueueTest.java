package util;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<DelayObject> delayQueue = new DelayQueue<>();
        delayQueue.add(new DelayObject(5, TimeUnit.SECONDS, "test"));
        long start = System.nanoTime();
        DelayObject take = null;
        try {
            take = delayQueue.take();
        } catch (InterruptedException ignored) {
        }
        if (take != null) {
            long end = System.nanoTime();
            System.out.println(TimeUnit.NANOSECONDS.toMillis(end - start));
        }

    }

    static class DelayObject implements Delayed {
        private final long future;
        private final TimeUnit unit;
        private final Object key;

        public DelayObject(long time, TimeUnit unit, Object key) {
            this.future = unit.toMillis(time) + System.currentTimeMillis();
            this.unit = TimeUnit.MILLISECONDS;
            this.key = key;
        }

        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            return unit.convert(this.future - System.currentTimeMillis(), this.unit);
        }

        @Override
        public int compareTo(@NotNull Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
