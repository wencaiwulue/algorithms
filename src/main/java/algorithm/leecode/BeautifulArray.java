package algorithm.leecode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * https://leetcode-cn.com/problems/beautiful-array/
 *
 * @author fengcaiwen
 * @since 11/8/2019
 */
public class BeautifulArray {
    public static void main(String[] args) {
        int n = 8;
        int[] ints = testIdea3(n);
        System.out.println(Arrays.toString(ints));
        check(ints);
    }

    /*
     * 发现这道题的解会有许多种，这里的组合结果为4*4=16种，也就是给定一个N，可能的解有16种，但是对于特别短小的N，
     * 解法可能没有16种这么多，但是对于N大一点儿来说，都是可以构造的。
     * */
    private static int[] testIdea3(int N) {
        int n = N % 3 == 0 ? N / 3 : N / 3 + 1;
        int radix = BigDecimal.valueOf(log2(n)).setScale(0, RoundingMode.UP).intValue();
        // 大于n的最小的2的次方数，比如n=9，则c=16
        int c = (int) Math.pow(2, radix);
        int[] ints = new int[3 * c];
        // 采用231模式, 还可以使用132, 213, 312模式
        ints[0] = 1 + c;
        ints[1] = 1;
        ints[2] = 1 + 2 * c;
        int size = 3;
        long start = System.nanoTime();
        // 这里的递增方式是向后递增, 也可以变化, 比如向前递增或者前后交替递增, 后前交替递增。
        for (int i = radix - 1; i >= 0; i--) {
            int base = (int) Math.pow(2, i);
            for (int j = 0; j < size; j++)
                ints[size + j] = ints[j] + base;
            size *= 2;
        }
        long end = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(end - start) + "毫秒");
        int[] result = new int[N];
        int p = 0;
        for (int i : ints) {
            if (i <= N) {
                result[p++] = i;
            }
        }
        return result;
    }

    private static double log2(double N) {
        return Math.log(N) / Math.log(2);
    }

    private static void check(int[] array) {
        int c = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    if (array[i] + array[k] == 2 * array[j]) {
                        System.out.printf("%s + %s = 2 * %s\n", array[i], array[k], array[j]);
                        c++;
                    }
                }
            }
        }
        System.out.println(c);
    }

    private static int middle(int a, int b, int c) {
        int max = Math.max(Math.max(a, b), c);
        int min = Math.min(Math.min(a, b), c);
        if (max == a) {
            if (min == b) {
                return c;
            } else {
                return b;
            }
        } else if (max == b) {
            if (min == a) {
                return c;
            } else {
                return a;
            }
        } else {
            if (min == a) {
                return b;
            } else {
                return a;
            }
        }
    }
}
