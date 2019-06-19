package trie;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 6/19/2019
 */
public class ACTest {
    public static void main(String[] args) {

        Arrays.stream(test("chinchilla")).forEach(System.out::println);
    }

    private static int[] test(String str) {

        int m = str.length();
        char[] chars = str.toCharArray();

        int[] next = new int[m];
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
}
