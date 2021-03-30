package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengcaiwen
 * @since 8/5/2019
 */
public class TotalCandyTest {
    public static void main(String[] args) {
        int[] ints = new int[]{-3, -25, 20, 20, 10, -3, 6, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        totalCandy(ints);
    }

    private static void totalCandy(int[] n) {
        // assume n.length less than a;
        long a = 0;
        int sum = 0;
        Map<Integer, Integer> rankMap = pareOperate(n);
        for (int i = n.length - 1; i >= 0; i--) {
            Integer rank = rankMap.get(n[i]);
            if (isHighOrLow(a, rank) == 0)
                a = setRankReverse(a, rank);
            String s2 = "0" + Long.toBinaryString(a);
            String s = s2.substring(s2.length() - rank);
//            String s1 = s.replaceAll("1", "");
//            sum += (s.length() - s1.length());
            if (!s.equals(""))
                sum += NumberOf1(Integer.parseInt(s, 2));
        }
        System.out.println("total: " + sum);
    }

    /**
     * get rank of all elements
     */
    private static Map<Integer, Integer> pareOperate(int[] n) {
        int[] ints = Arrays.stream(n).sorted().toArray();
        Map<Integer, Integer> map = new HashMap<>(n.length);
        for (int i = 0; i < n.length; i++) {
            map.put(ints[i], i);
        }
        return map;
    }

    private static int NumberOf1(int m) {
        int n = m;
        n = (n & 0x55555555) + ((n >> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >> 16) & 0x0000ffff);
        return n;
    }

    private static long setRankReverse(long a, int rank) {
        return ~((~a) ^ (1L << rank));
    }

    public static int isHighOrLow(long a, int rank) {
        return (int) (1L & (a >> rank));
    }

}
