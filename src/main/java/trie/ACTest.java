package trie;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 6/19/2019
 */
public class ACTest {
    public static void main(String[] args) {
        String s = "aaaa";

        Arrays.stream(build(s)).forEach(System.out::println);
        System.out.println("-----------------------------");
        Arrays.stream(build0(s)).forEach(System.out::println);
//        pare("chinchilla", "chinchilla");
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
        return next;
    }

    /**
     * copy
     */
    private static int[] build0(String str) {

        int m = str.length();
        char[] chars = str.toCharArray();

        int[] next = new int[m + 1];
        next[0] = -1;
        int i = 0, j = -1;

        while (i < m) {
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

    public static void pare(String content, String pattern) {
        int[] next = build(pattern);

        char[] contentChar = content.toCharArray();
        char[] patternChar = pattern.toCharArray();

        int n = contentChar.length;
        int m = patternChar.length;

        int i = 1, j = 0;
        while (i < n - m && j != m) {
            int a = contentChar[i];
            int b = patternChar[j];
            if (a == b) {
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
