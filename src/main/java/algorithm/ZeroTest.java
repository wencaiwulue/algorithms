package algorithm;

/**
 * leetcode:
 * https://leetcode-cn.com/problems/k-th-symbol-in-grammar/submissions/
 *
 * @author fengcaiwen
 * @since 11/1/2019
 */
public class ZeroTest {
    public static void main(String[] args) {
        System.out.println(test(2, 2));
        System.out.println(run(2, 2));
    }

    public static int run(int m, int n) {
        return test(m, n) == -1 ? 0 : 1;
    }

    public static int test(int m, int n) {
        if (m == 1) return n == 1 ? -1 : 1;

        return n % 2 == 0 ? test(m - 1, n / 2) * -1 : test(m - 1, n / 2 + 1);
    }
}
