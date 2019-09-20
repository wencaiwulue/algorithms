package trie;


import java.util.*;

/**
 * @author fengcaiwen
 * @since 6/19/2019
 */
public class ACTest {
    public static void main(String[] args) {
        // chinchilla, sensitive word check
//        String txt = "钓上一个大王八，大概有五十斤王八";
//        String pattern = "王八";

//        Arrays.stream(build(txt)).forEach(System.out::println);
//        System.out.println("-----------------------------");
//        Arrays.stream(build0(pattern)).forEach(System.out::println);
//        List<Integer> integers = checkv1(txt, pattern);
//        System.out.println(integers);
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};
        List<Integer> substring = findSubstring(s, words);
        System.out.println(substring);
        System.out.println(1 ^ 2 ^ 3 ^ 4);
        System.out.println(1 ^ 2 ^ 3 ^ 12);
        System.out.println();
    }

    /**
     * by myself
     */
    private static int[] build(String pattern) {

        int m = pattern.length();
        char[] t = pattern.toCharArray();

        int[] next = new int[m + 1];
        next[0] = -1;
        int i = 1, j = 0;

        while (i < m) {
            char a = t[i];
            char b = t[j];
            if (a == b) {
                next[i + 1] = next[i] + 1;
                i++;
                j++;
            } else {
                i++;
            }
        }
        int[] ints = Arrays.copyOf(next, m);
        return next;
    }

    /**
     * copy
     */
    private static int[] build0(String pattern) {

        int m = pattern.length();
        char[] chars = pattern.toCharArray();

        int[] next = new int[m];
        next[0] = -1;
        int i = 0, j = -1;

        while (i < m - 1) {
            if (j < 0 || chars[i] == chars[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    private static void check(String txt, String pattern) {
        int[] next = build(pattern);

        char[] t = txt.toCharArray();
        char[] p = pattern.toCharArray();

        int n = t.length;
        int m = p.length;

        int i = 0, j = 0;
        while (i < n && j != m) {
            if (j < 0 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == m) {
            System.out.println("found");
        } else {
            System.out.println("not found");
        }

    }

    private static List<Integer> check_v1(String txt, String pattern) {
        int[] next = build(pattern);

        char[] t = txt.toCharArray();
        char[] p = pattern.toCharArray();

        int n = t.length;
        int m = p.length;

        List<Integer> index = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n) {

            if (j < 0 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }

            if (m == j) {
                index.add(i - m);
                j = -1;
            }
        }
        return index;
    }

    /*
     * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     */
    public static List<Integer> findSubstring(String s, String[] words) {

        if (null == s || "".equals(s)) return new ArrayList<>();

        List<Integer[]> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            List<Integer> integers1 = check_v1(s, words[i]);
//            System.out.println(integers1);
            Integer[] integers = integers1.toArray(new Integer[0]);
            list.add(integers);
//            System.out.println(Arrays.toString(integers));
        }

        List<Integer> finalR = new ArrayList<>();
        int minLength = Integer.MAX_VALUE;
        for (Integer[] integers : list) {
            minLength = Math.min(integers.length, minLength);
        }

        for (int j = 0; j < minLength; j++) {
            int d = 0;
            if (words.length != 0)
                d = words[0].length();

            int last = -1;
            int min = Integer.MAX_VALUE;
//            for (int i = 0; i < list.size(); i++) {
//                Integer integer = list.get(i)[j];
//                if (min > integer)
//                    min = integer;
//                if (last != -1) {
//                    if (Math.abs(integer - last) == d) {
//                        finalR.add(min);
//                        min = Integer.MAX_VALUE;
//                    }
//                }
//                last = integer;
//
//            }

            Map<Integer, Integer> rankMap = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                for (Integer integer : list.get(i)) {
                    rankMap.put(integer, i + 1);
                }
            }
            int dd = 0;
            if (words.length != 0)
                dd = words[0].length();
            if (dd == 0) return new ArrayList<>();
            for (int i = 0; i < s.length() / dd; i++) {
                int m = Integer.MAX_VALUE;

                BitSet set = new BitSet(words.length);
                for (int k = 0; k < words.length; k++) {
                    System.out.println(i * dd + k * dd);
                    if (rankMap.containsKey(i * dd + k * dd)) {
                        set.set(rankMap.get(i * dd + k * dd), true);
                        m = Math.min(m, rankMap.getOrDefault(i * dd + k * dd, Integer.MAX_VALUE));
                    }
                }

                boolean flag = true;
                for (int k = 0; k < words.length; k++) {
                    flag = flag && set.get(k + 1);
                }
                if (flag && set.length() > 0)
                    finalR.add(m);
            }


        }
        return finalR;

    }

}
