package algorithm.leecode;

import org.junit.Test;

/**
 * @author fengcaiwen
 * @since 9/26/2019
 */
public class ReverseInteger {

    @Test
    public void main() {
        int x = 123123;
        System.out.println("x = " + x);
        System.out.println("reverseInteger(x) = " + reverseInteger(x));
    }

    public int reverseInteger(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
