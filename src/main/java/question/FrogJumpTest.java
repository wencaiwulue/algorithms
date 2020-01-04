package question;

/**
 * @author fengcaiwen
 * @since 7/2/2019
 */
public class FrogJumpTest {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(climbStairs0(n));
        System.out.println(climbStairs(n));
    }

    /**
     * 可以跳1~n个台阶
     * <p>
     * n=1 1 --1种
     * n=2 11 2 --2种
     * n=3 111 12 21 3 --4种
     * n=4 1111 112 121 211 22 13 31 4 --8种
     * n=5 11111 1112 1121 1211 2111 122 221 212 113 131 311 32 23 14 41 5 --16种
     * 归纳：
     * f(n) = 2^(n-1)
     */
    private static int climbStairs0(int n) {
        return (int) Math.pow(2, n - 1);
    }

    /**
     * 只能跳1或者2个台阶
     * <p>
     * n=1 1 --1种
     * n=2 11 2 --2种
     * n=3 111 12 21 --3种
     * n=4 1111 112 121 211 22 --5种
     * n=5 11111 1112 1121 1211 2111 122 221 212 --8种
     * 归纳：
     * f(n) = f(n-1) + f(n-2)
     */
    private static int climbStairs(int i) {
        if (i == 2) return 2;
        if (i == 1) return 1;
        return climbStairs(i - 1) + climbStairs(i - 2);
    }

}
