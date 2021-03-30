package algorithm.leecode;

import org.junit.Test;

public class IsPalindrome {
    @Test
    public void isPalindrome() {
        System.out.println("boolean = " + isPalindrome(12213));
        System.out.println("boolean = " + isPalindrome(12321));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int y = x;
        int newNum = 0;
        while (x != 0) {
            int i = x % 10;
            newNum = newNum * 10 + i;
            x = x / 10;
        }
        return newNum == y;
    }
}
