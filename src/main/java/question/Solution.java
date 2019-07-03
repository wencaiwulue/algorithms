package question;

/**
 * @author fengcaiwen
 * @since 7/2/2019
 */
public class Solution {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(climbStairs0(n));
        System.out.println(climbStairs(n));
    }

    /**
     * n=1 1 --1种
     * n=2 11 2 --2种
     * n=3 111 12 21 3 --4种
     * n=4 1111 112 121 211 13 31 4 --7种
     * n=5 11111 1112 1121 1211 2111 113 131 311 14 41 5 --11种
     * 归纳：
     * f(n) = 1+2+3+4+..+n-1 + 1
     */
    private static int climbStairs0(int n) {
        return n * (n - 1) / 2 + 1;
    }

    /**
     * n=1 1 --1种
     * n=2 11 2 --2种
     * n=3 111 12 21 --3种
     * n=4 1111 112 121 211 --4种
     * n=5 11111 1112 1121 1211 2111 --5种
     * 归纳：
     * f(n) = n
     */
    private static int climbStairs1And2(int n) {
        return n * (n - 1) / 2 + 1;
    }

    public static int climbStairs(int n) {
        return climb_Stairs(0, n);
    }

    public static int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }

}
