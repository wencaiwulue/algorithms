package trie;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 6/19/2019
 */
public class ACTest {
    public static void main(String[] args) {
        String s = "chinchilla";

        Arrays.stream(build(s)).forEach(System.out::println);
//        System.out.println("-----------------------------");
//        Arrays.stream(build0(s)).forEach(System.out::println);
        pare("chinchilla", "cha");
    }

    /**
     * by myself
     */
    private static int[] build(String str) {

        int m = str.length();
        char[] chars = str.toCharArray();

        int[] next = new int[m + 1];
        next[0] = -1;
        int i = 1, j = 0;

        while (i < m) {
            char a = chars[i];
            char b = chars[j];
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
    private static int[] build0(String str) {

        int m = str.length();
        char[] chars = str.toCharArray();

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

    private static void pare(String content, String pattern) {
        int[] next = build(pattern);

        char[] t = content.toCharArray();
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
//        System.out.println(j);
//        System.out.println(i);
//        System.out.println(m);
        if (j == m) {
            System.out.println("ok");
        }

    }
}
