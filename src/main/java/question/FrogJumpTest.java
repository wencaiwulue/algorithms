package question;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

/**
 * @author fengcaiwen
 * @since 7/2/2019
 */
public class FrogJumpTest {


    static final int SHARED_SHIFT = 16;
    static final int SHARED_UNIT = (1 << SHARED_SHIFT);
    static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;


    public static void main(String[] args) {
        int n = 5;
        System.out.println(climbStairs0(n));
        System.out.println(climbStairs(n));

        System.out.printf("EXCLUSIVE_MASK: %s\n", Integer.toBinaryString(EXCLUSIVE_MASK));
        System.out.printf("SHARED_UNIT: %s\n", Integer.toBinaryString(SHARED_UNIT));
        System.out.printf("MAX_COUNT:\t%s\t%s\t%s\n", MAX_COUNT, Integer.toBinaryString(MAX_COUNT), Integer.toBinaryString(MAX_COUNT).length());


//        /** Returns the number of shared holds represented in count  */
//        static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
//        /** Returns the number of exclusive holds represented in count  */
//        static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

        int c = 1 + EXCLUSIVE_MASK;

        System.out.printf("sharedCount:\t%s\n", c >>> SHARED_SHIFT);
        System.out.printf("sharedCount:\t%s\n", Integer.toBinaryString(c >>> SHARED_SHIFT));
        System.out.printf("exclusiveCount:\t%s\n", c & EXCLUSIVE_MASK);
        System.out.printf("exclusiveCount:\t%s\n", Integer.toBinaryString(c & EXCLUSIVE_MASK));
        System.out.printf("%s:\t\t   %s\n", c, Integer.toBinaryString(c));
        System.out.printf("SHARED_UNIT:\t%s bit\n", Integer.toBinaryString(SHARED_UNIT).length());
        System.out.printf("EXCLUSIVE_MASK:\t%s bit\n", Integer.toBinaryString(1 + EXCLUSIVE_MASK).length());
        System.out.println("10000000000000000".length());


//        System.out.printf("lower:\t%s\n", Integer.parseInt("1011001000000111", 2));
//        System.out.printf("test:\t%s\n", Integer.parseInt("100000000000000", 2));
//        System.out.println("100000000000000".length());
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
