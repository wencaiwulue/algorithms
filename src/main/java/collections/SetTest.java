package collections;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 7/11/2019
 */
public class SetTest {

    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 2, 3, 4, 6};
        int[] b = new int[]{6, 9, 0, 1, 2, 3, 4, 5};
        System.out.println(test(a, b));
    }

    /**
     * judge whether a ⊆ b or not
     */
    public static boolean test(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        for (int j = 0; j < b.length && i < a.length; j++)
            if (a[i] == b[j])
                i++;

        return i == a.length;
    }
}
