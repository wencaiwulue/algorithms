package lock;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author naison
 * @since 4/28/2020 18:39
 */
public class MapSplitTest {
    public static void main(String[] args) {
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            map.put(i, i);
        }

        int processors = Runtime.getRuntime().availableProcessors();

        AtomicInteger ai0 = new AtomicInteger(0);
        AtomicInteger ai1 = new AtomicInteger(0);
        AtomicInteger ai3 = new AtomicInteger(0);
        long start = System.nanoTime();
        int x = 0;
        Spliterator<Map.Entry<Integer, Object>> entrySpliterator = map.entrySet().spliterator().trySplit();
        System.out.println(++x);
        entrySpliterator.forEachRemaining(integerObjectEntry -> {
            ai0.incrementAndGet();
            return;
        });

        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();


        map.entrySet().stream().forEach(e -> {
            ai1.incrementAndGet();
            return;
        });
        System.out.println(System.nanoTime() - start);
        System.out.println(ai0.get());
        System.out.println(ai1.get());


        start = System.nanoTime();
        Set<Map.Entry<Integer, Object>> entrySet = map.entrySet();
        int step = (int) Math.ceil(entrySet.size() / (double) processors);
        List<Iterator<Map.Entry<Integer, Object>>> list = new ArrayList<>(processors);
        for (int i = 0; i < processors; i++) {
            Iterator<Map.Entry<Integer, Object>> iterator = entrySet.iterator();
            int m = i * step;
//            int n = Math.min((i + 1) * step, entrySet.size());
            for (int j = 0; j < m; j++) {
                iterator.next();
            }
            list.add(iterator);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                Iterator<Map.Entry<Integer, Object>> entryIterator = list.get(i);
                for (int j = 0; j < step; j++) {
                    entryIterator.next();
                    ai3.incrementAndGet();
                }
            } else {
                int min = Math.min(i * step, entrySet.size());
                for (int j = 0; j < min; j++) {
                    ai3.incrementAndGet();
                }
            }
        }

        System.out.println(System.nanoTime() - start);
        System.out.println(ai3);

    }
}
