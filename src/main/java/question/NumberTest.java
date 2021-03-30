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
        System.out.println(none);

        int b = reduction(none, String.valueOf(a).length());
        System.out.println(b);
    }

    private static int transfer(int a) {
        char[] chars = String.valueOf(a).toCharArray();
        int sum = 1;
        for (int i = 0; i < chars.length; i++) {
            int val = (int) chars[i];
            int prime = generateTopNPrimeNumber(1000, i + 1);
            sum *= Math.pow(prime, (val + 1));
        }
        return sum;
    }

    // todo
    // assume we already know this info and sum
    private static int reduction(int sum, int length) {
        StringBuilder str = new StringBuilder();
        for (int i = length; i > 1; i--) {
            int a = generateTopNPrimeNumber(1000, i);
            int j = 0;
            while (sum % a != 0) {
                sum = sum % a;
                j++;
            }
            str.append(j);
        }
        return Integer.parseInt(str.toString());
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
