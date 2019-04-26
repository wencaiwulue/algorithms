package Algorithm;

/**
 * @author fengcaiwen
 * @since 4/26/2019
 * <p>
 * ⌊c/d⌋ = max{ x | d * x <= c} = max{ x | d * x < c + 1 }
 */
public class FloorTest {
    public static void main(String[] args) {
        int dividend = 5;
        int divisor = 3;
        System.out.println(floor(dividend, divisor));
    }

    private static int floor(int dividend, int divisor) {
        int length = 0;
        while (dividend > divisor) {
            dividend -= divisor;
            length++;
        }
        return length;
    }
}
