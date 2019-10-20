package algorithm.leecode;

/**
 * @author fengcaiwen
 * @since 9/26/2019
 */
public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverse(Integer.MAX_VALUE));
        String s = "9646324351";
        System.out.println(s);
        System.out.println(Integer.MAX_VALUE);
    }

    public static int reverse(int x) {
        try {
            if (x >= Integer.MAX_VALUE) return 0;
            boolean positive = x > 0;
            String s = reverseString(String.valueOf(Math.abs(x)));
            int i = Integer.parseInt(s);
            return positive ? i : -i;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0, j = length - 1; i < length && j > i; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}
