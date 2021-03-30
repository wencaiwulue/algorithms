package algorithm;

import java.util.*;
import java.util.stream.Stream;

public class Candy {
    public static Map<Integer, Integer> test(int[] a) {
        List<Integer> list = new ArrayList<>();
        for (int i : a) {
            list.add(i);
        }
        Map<Integer, Integer> map = new HashMap<>();
        Object[] objects = list.stream().sorted().toArray();
        for (int i = 0; i < objects.length; i++) {
            map.put(Integer.valueOf(String.valueOf(objects[i])), i);
        }

        return map;
    }


    public static int run(int[] a) {
        Map<Integer, Integer> map = test(a);
        // init with 0
        Map<Integer, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            counterMap.put(i, 0);
        }
        int total = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            int j = counterMap.get(map.get(a[i]));
            counterMap.put(map.get(a[i]), map.get(a[i]) + 1);
            total += j;
        }

        return total;

    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 1, 3, 2};
        System.out.println(run(a));

        // chain
        Thread thread = new Thread(() -> {
            System.out.println("f1");
        });

        Thread thread1 = new Thread(() -> {
            thread.start();
            System.out.println("f2");
        });
        Thread thread2 = new Thread(() -> {
            thread1.start();
            System.out.println("f3");
        });
        thread2.start();

//        Runnable runnable = () -> {
//            System.out.println("f1");
//        };
//        Runnable runnable1 = () -> {
//            System.out.println("f2");
//        };
        Runnable runnable2 = () -> {
            Runnable runnable3 = () -> {
                Runnable runnable4 = () -> {
                    System.out.println("f3");
                };
                runnable4.run();
                System.out.println("f2");
            };
            runnable3.run();
            System.out.println("f1");
        };
        runnable2.run();


        int[] s = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        System.out.println(Arrays.toString(maxSubSet(a)));

    }

    public static int[] maxSubSet(int[] a) {
        List<Integer> list = new ArrayList<>();
        int total = 0;
        int j = 0;
        int k = 0;
        boolean flag = false;
        int n = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= 0) {
                if (flag) {
                    list.add(total - n);
                    total = 0;
                    j = k = i;
                } else {
                    flag = true;
                    n = a[i];
                    total += n;
                }
            } else {
                total += a[i];
                k++;
            }

        }
        int[] b = new int[k - j];
        System.arraycopy(a, j, b, 0, k - j);
        return b;
    }

}
