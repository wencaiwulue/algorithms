package demo.ORMTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

/**
 * @author fengcaiwen
 * @since 7/25/2019
 */
public class LruTest {
    private static Function<Integer, Integer> func = (e) -> {
        return e + 1;
    };


    private static int core = 20;
    private static Stack<Integer> stack = new Stack<>();
    private static Map<Integer, Integer> map = new HashMap<>(core);
    private static Integer lastVisitKey;

    public static void lruBaseOnStack(Integer i, Stack<Integer> stack) {
        // O(n)
        stack.remove(i);
        // O(1)
        stack.push(i);
        int size = stack.size();
        if (size > core) {
            // O(1)
            stack.remove(size);
        }
    }

    public static void lruBaseOnHashMap(Integer i, Map<Integer, Integer> map) {
//        lastVisitKey = map.
//        map.remove(i);
        map.put(1, i);
        if (map.size() > core) {
            map.remove(lastVisitKey);
        }
        Integer apply = func.apply(1);
    }

    public static void main(String[] args) {

    }

    public static void test(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int length = a.length - 1; length >= 0; length--) {
            map.putIfAbsent(a[length], 1);
            Integer integer = map.get(a[length]);
            map.put(a[length], ++integer);
        }
    }

}
