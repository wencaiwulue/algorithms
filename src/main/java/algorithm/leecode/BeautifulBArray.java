package algorithm.leecode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

/**
 * https://leetcode-cn.com/problems/beautiful-array/
 *
 * @author fengcaiwen
 * @since 11/8/2019
 */
public class BeautifulBArray {
    public static void main(String[] args) {
        int n = 100000;
        check(testIdea3(n));
    }

    private static int[] testIdea3(int N) {
        int i = N % 3 == 0 ? N / 3 : N / 3 + 1;
        int value = BigDecimal.valueOf(log2(i)).setScale(0, RoundingMode.UP).intValue();
        int f = (int) Math.pow(2, value);
        int[] ints = new int[3 * f];
        ints[0] = 1 + f;
        ints[1] = 1 + 2 * f;
        ints[2] = 1;
        int size = 3;
        long start = System.nanoTime();
        for (int j = value - 1; j >= 0; j--) {
            int base = (int) Math.pow(2, j);
            for (int i1 = 0; i1 < size; i1++)
                ints[size + i1] = ints[i1] + base;
            size *= 2;
        }
        long end = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(end - start) + "毫秒");
        int[] result = new int[N];
        int p = 0;
        for (int i1 : ints) {
            if (i1 <= N) {
                result[p++] = i1;
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

    private static int mid(int a, int b, int c) {
        int max = Math.max(Math.max(a, b), c);
        int min = Math.min(Math.min(a, b), c);
        if (max == a) {
            if (min == b) {
                return c;
            } else {
                return b;
            }
        } else if (b == max) {
            if (a == min) {
                return c;
            } else {
                return a;
            }
        } else {
            if (a == min) {
                return b;
            } else {
                return a;
            }
        }
    }
}
