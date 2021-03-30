package tree;

/**
 * @author fengcaiwen
 * @since 6/12/2019
 */
public class RestoreTest {

    // todo
    private static int test1(int[] pre, int from, int length, int[] in, int in_from, int in_to, BinTree<Integer> tree) {
        int root = pre[from];
        tree.insert(root);
        int i = indexOf(in, in_from, in_to, root);
        int left = test1(pre, from, i - from, in, i, i - in_from, tree);
        tree.insert(left);
        int right = test1(pre, i, i + length, in, i, i - in_from, tree);
        tree.insert(right);

//        int[] in_left = Arrays.copyOfRange(in, 0, i);
//        int[] in_right = Arrays.copyOfRange(in, i, in.length - i);
        return -1;

    }

    public static int indexOf(int[] arrays, int from, int length, int value) {
        for (int i = from; i < length; i++) if (arrays[i] == value) return i;
        return -1;
    }

    public static void main(String[] args) {
    }
}
