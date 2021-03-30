package thread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import util.ThreadUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class TimeWheel {
    private final int level;
    private final int[] p;
    private final long[] dial;
    private final long step;
    private final List<Task>[] tasks;

    @SuppressWarnings("unchecked")
    public TimeWheel(int level, long[] dial, long step) {
        this.level = level;
        this.p = new int[level];
        this.dial = dial;
        this.step = step;
        this.tasks = new LinkedList[(int) Arrays.stream(this.dial).asDoubleStream().reduce(1, Double::sum)];
        for (int i = 0; i < this.tasks.length; i++) {
            this.tasks[i] = new LinkedList<>();
        }
        this.init();
    }

    private long multiplication(int level) {
        long j = 1;
        for (int i = 0; i < level; i++) {
            j *= this.dial[i];
        }
        return j;
    }

    private long addition(int level) {
        int j = 0;
        for (int i = 0; i < level; i++) {
            j += this.dial[i];
        }
        return j;
    }

    private void init() {
        Runnable r =
                () -> {
                    this.p[0] += this.step;
                    //1, just imagine the progress of clock: 23:59:59 --> 00:00:00
                    // get effect level
                    int e = 0;
                    for (int i = 0; i < this.level; i++) {
                        long quotient = this.p[i] / this.dial[i];
                        long remainder = this.p[i] % this.dial[i];
                        this.p[i] = (int) remainder;
                        if (quotient == 0) {
                            break;
                        } else {
                            this.p[i + 1] += quotient;
                            e = i + 1;
                            if (this.p[this.level - 1] == this.dial[this.level - 1]) {
                                e = this.level - 1;
                                // 23:59:59 --> 00:00:00
                                for (int j = 0; j < this.level; j++) {
                                    this.p[j] = 0;
                                }
                                break;
                            }
                        }
                    }

                    // 2, drop the task to the lower level
                    long start = System.nanoTime();
                    for (int level = e; level > 0; level--) {
                        long l = this.p[level] + addition(level);
                        List<Task> taskList = this.tasks[(int) l];
                        for (Task task : taskList) {
                            int time = (int) task.unit.toMillis(task.period);
                            long upper = multiplication(level);
                            long lower = multiplication(level - 1);
                            // avoid decimals
                            long remainder = time - (time / upper) * upper;
                            long bucket = remainder / lower;

                            long position = ((bucket + this.p[level - 1]) % this.dial[level - 1]) + addition(level - 1);
                            this.tasks[(int) position].add(task);
                        }
                        taskList.clear();
                    }
                    long l = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
                    if (l >= 1) {
                        System.out.println("spend: " + l + "ms");
                    }

                    // 3, run the lowest level task
                    List<Task> taskList = this.tasks[this.p[0]];
                    for (Task task : taskList) {
                        ThreadUtil.getThreadPool().submit(() -> {
                            this.scheduleAtFixedRateInner(task);
                            task.runnable.run();
                        });
                    }
                    taskList.clear();
                };
        ThreadUtil.getThreadPool().submit(() -> {
            long sleep = TimeUnit.MILLISECONDS.toNanos(this.step);
            //noinspection InfiniteLoopStatement
            while (true) {
                // maybe using parkUntil ?? a abstract time
                LockSupport.parkNanos(sleep);
                ThreadUtil.getThreadPool().submit(r);
            }
        });
    }

    public Task scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        Task task = Task.of(command, initialDelay, period, unit);
        Runnable runnable = () -> {
            this.scheduleAtFixedRateInner(task);
            task.runnable.run();
        };
        if (initialDelay <= 0) {
            ThreadUtil.getThreadPool().submit(runnable);
        } else {
            FakeDelayQueue.delay(FakeDelayQueue.DelayTask.of(task.getFutureInMills(), runnable));
        }
        return task;
    }

    private Task scheduleAtFixedRateInner(Task task) {
        int l = (int) task.unit.toMillis(task.period);
        int level = 0;
        int s = 1;
        for (int i = 0; i < this.level; i++) {
            if (l < this.dial[i] * s) {
                level = i;
                break;
            } else {
                s *= this.dial[i];
            }
        }
        int bucket = (int) (l / multiplication(level));
        // insert into relative bucket, not abstract bucket
        long position = (bucket + this.p[level]) % this.dial[level] + addition(level);
        this.tasks[(int) position].add(task);
        return task;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Task {
        private Runnable runnable;
        private long initialDelay;
        private long period;
        private TimeUnit unit;

        public long getFutureInMills() {
            return System.currentTimeMillis() + this.unit.toMillis(this.initialDelay);
        }

        public static Task of(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
            return new Task(runnable, initialDelay, period, unit);
        }
    }
}
