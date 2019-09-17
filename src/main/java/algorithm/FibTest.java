package algorithm;

/**
 * @author fengcaiwen
 * @since 9/16/2019
 */
public class FibTest {
    private static int fibRecursive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    private static int fibIterator(int n) {
        int fibm = 0;
        int fibn = 1;
        int j = 0;
        while (++j < n) {
            fibn += fibm;
            fibm = fibn - fibm;
        }
        return fibn;
    }

    public static void main(String[] args) {
        int n = 7;
        System.out.println(fibRecursive(n));
        System.out.println(fibIterator(n));
    }
}
