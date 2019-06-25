package trie;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 6/19/2019
 */
public class ACTest {
    public static void main(String[] args) {
        // chinchilla, sensitive word check
        String txt = "钓上一个大王八，大概有五十斤";
        String pattern = "王八蛋";

//        Arrays.stream(build(txt)).forEach(System.out::println);
//        System.out.println("-----------------------------");
//        Arrays.stream(build0(pattern)).forEach(System.out::println);
        check(txt, pattern);
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
}
