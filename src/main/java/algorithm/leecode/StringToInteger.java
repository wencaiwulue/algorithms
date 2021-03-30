package algorithm.leecode;

import org.junit.Test;

public class StringToInteger {
    @Test
    public void leetcode8StringToInteger() {
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("+-12"));
        System.out.println(myAtoi("12"));
    }

    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int x = 0;
        Boolean isPositive = null;
        boolean beyand = false;
        for (char c : chars) {
            if (c == '+') {
                if (isPositive != null) {
                    return 0;
                }
                isPositive = true;
            } else if (c == '-') {
                if (isPositive != null) {
                    return 0;
                }
                isPositive = false;
            } else if (c == ' ') {
            } else if (c == '.') {
                break;
            } else if (48 <= c && c <= 57) {
                if (x > Integer.MAX_VALUE / 10) {
                    beyand = true;
                    break;
                } else if (x < Integer.MIN_VALUE / 10) {
                    beyand = true;
                    break;
                }
                x = x * 10 + (c - 48);
            } else {
                return x;
            }
        }
        if (beyand) {
            return isPositive == null || isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return isPositive == null || isPositive ? x : -x;
    }
}
