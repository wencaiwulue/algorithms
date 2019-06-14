package sort;

/**
 * not base on compare
 *
 * @author fengcaiwen
 * @since 6/6/2019
 */
public class BasicBucketSort {

    public static void main(String[] args) {

        int[] a = new int[]{3, 4, 5, 6, 7, 8, 1, 2};
        int[] ints = test(a);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    private static int[] test(int[] a) {

        int[] b = new int[1024];
        for (int i : a) {
            b[i] = i;
        }

        int j = 0;
        for (int i : b) {
            if (i != 0) {
                a[j++] = i;
            }
        }

        return a;
    }
}
