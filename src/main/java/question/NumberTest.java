package question;

import java.util.BitSet;

/**
 * @author fengcaiwen
 * @since 6/25/2019
 */
public class NumberTest {
    public static void main(String[] args) {
        int a = 31415962;
        System.out.println(a);
        int none = transfer(a);
        int b = reduction(none, String.valueOf(a).length());
        System.out.println(b);
    }

    private static int transfer(int a) {
        char[] chars = String.valueOf(a).toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            int val = (int) chars[i];
            int l = generateTopNPrimeNumber(1000, i + 1);
            sum += l ^ (val + 1);
        }
        System.out.println(sum);

        // assume we already know this info and sum
        int b = chars.length;
        return sum;
    }

    private static int reduction(int sum, int length) {
        return 0;
    }

    // generate the level-th pure number
    public static int generateTopNPrimeNumber(int range, int n) {
        BitSet bitSet = PrimeNumber.calculate(range);
        for (int j = 0, i = 1; j < bitSet.length(); j++)
            if (bitSet.get(j))
                if (i++ == n)
                    return j;

        // if reach there, means this range do not contains n prime number, then double range and try again
        return generateTopNPrimeNumber(2 * range, n);
    }
}
