package question;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.BitSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author fengcaiwen
 * @since 4/19/2019
 * <p>
 * 第1步：确定需要筛选素数的范围，确定范围的最大值，比如是120。
 * 第2步：根号120的结果为10.95，所以只需要利用11以内所有素数的倍数来剔除120以内的数字，剩下的就是素数。首先剔除以2为倍数的数字，11以内剔除掉4，6，8，10这几个数字，同时剔除掉120以内所有以2为倍数的数字。
 * 第3步：最小的未被剔除的数字为3，剔除以3为倍数的数字，11以内剔除9这个数字，同时剔除掉120以内所有以3为倍数的数字。
 * 第4步：最小的未被剔除的数字为5，剔除以5为倍数的数字，11以内不需要剔除数字，同时剔除掉120以内所有以5为倍数的数字。……如此类推，可以将120以内的所有素数完全找到。
 */
public class PureNumber {


    public static void main(String[] args) throws InterruptedException {
        int a = 10000000;
        cal(a);
    }

    private static void cal(int range) throws InterruptedException {
        int a = BigDecimal.valueOf(Math.sqrt(range)).setScale(0, RoundingMode.FLOOR).intValue();
        BitSet bitSet = new BitSet(range);
        for (int i = 0; i < range; i++) {
            bitSet.set(i, true);
        }

//        CountDownLatch countDownLatch = new CountDownLatch(a - 2);

        BitSet bitSet1 = new BitSet(a);
        for (int i = 0; i < a; i++) {
            bitSet1.set(i, true);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10000);

        ConcurrentLinkedQueue<Future<?>> queue = new ConcurrentLinkedQueue<Future<?>>();

        for (int i = 2; i < a; i++) {
            if (bitSet1.get(i)) {
                int finalI = i;
                Future<?> future = executorService.submit(new Thread(() -> {
                    for (int j = 1; j < range; j++) {
                        if (j % finalI == 0) {
                            bitSet.clear(j);
                            bitSet1.clear(j);
                        }
                    }
                }));
                queue.add(future);
            }
        }

        int size = queue.size();

        while (true) {
            Iterator<Future<?>> iterator = queue.iterator();
            if (iterator.hasNext()) {
                Future<?> next = iterator.next();
                if (size == 0) {
                    break;
                }
                if (next.isDone()) {
                    size--;
                }

            }
        }


//        countDownLatch.await();

        for (int i = 0; i < range; i++) {
            if (bitSet.get(i)) {
                System.out.println(i);
            }
        }
    }
}
