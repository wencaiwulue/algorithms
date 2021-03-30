package util;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class DatetimeTest {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Long> map = new HashMap<>();
        map.put("a", 3L);
        map.put("b", 8L);
        map.put("c", 2L);
        map.put("d", 8L);
        map.put("e", 8L);
        map.values()
                .stream()
                .sorted()
                .collect(Collectors.groupingBy(e -> e))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() >= 3)
                .filter(e -> e.getKey() > 1)
                .max(Comparator.comparingLong(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .ifPresent(e -> System.out.println(e));

        RateLimiter rateLimiter = RateLimiter.create(1000D);
        Semaphore semaphore = new Semaphore(1000);
        rateLimiter.acquire();
        semaphore.acquire();

    }

}
