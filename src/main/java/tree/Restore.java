package tree;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author fengcaiwen
 * @since 6/12/2019
 */
public class Restore {
    public void test1(int[] pre, int[] in, BinTree tree) {
        int root = pre[0];
        int i = indexOf(in, root);
        int[] in_left = Arrays.copyOfRange(in, 0, i);
        int[] in_right = Arrays.copyOfRange(in, i, in.length - i);

    }

    public int indexOf(int[] arrays, int value) {
        for (int i = 0; i < arrays.length; i++) if (arrays[i] == value) return i;
        return -1;
    }

    public void test2(int[] suf, int[] in) {

    }

    public void test3(int[] pre, int[] suf) {

    }
}
