package question;

import java.util.BitSet;

/**
 * @author fengcaiwen
 * @since 6/25/2019
 */
public class NumberTest {
    public static void main(String[] args) {
        int a = 31415962;
        char[] chars = String.valueOf(a).toCharArray();
        for (int i = 0; i < chars.length; i++) {
//            if (a)
        }

        long l = generateTopNPrimeNumber(1000, 1);
        System.out.println(l);


    }

    // generate the level-th pure number
    public static long generateTopNPrimeNumber(int range, int n) {
        BitSet bitSet = PrimeNumber.calculate(range);
        for (int j = 0, i = 1; j < bitSet.length(); j++)
            if (bitSet.get(j))
                if (i++ == n)
                    return j;

        // if reach there, means this range do not contains n prime number, then double range and try again
        return generateTopNPrimeNumber(2 * range, n);
    }
}
