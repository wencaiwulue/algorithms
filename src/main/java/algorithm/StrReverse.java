package algorithm;

import java.math.BigDecimal;

/**
 * @author fengcaiwen
 * @since 7/8/2019
 */
public class StrReverse {
    public static void main(String[] args) {
        String s = "abcde";
        System.out.println(reverse(s));

        System.out.println(test("132"));
    }

    private static String test(String str) {
        if (str.equals(reverse(str))) {
            return str;
        } else {
            BigDecimal sum = new BigDecimal(str).add(new BigDecimal(reverse(str)));
            return test(sum.toString());
        }
    }

    private static String reverse(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0, j = chars.length - 1; i < chars.length && j > 0 && j > i; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return String.valueOf(chars);
    }
}
