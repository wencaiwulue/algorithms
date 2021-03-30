package algorithm.leecode;

import org.junit.Assert;
import org.junit.Test;

public class RomanInt {
    @Test
    public void romanInt() {
        Assert.assertEquals(3, romanToInt("III"));
        Assert.assertEquals(4, romanToInt("IV"));
        Assert.assertEquals(9, romanToInt("IX"));
        Assert.assertEquals(58, romanToInt("LVIII"));
        Assert.assertEquals(1994, romanToInt("MCMXCIV"));
        Assert.assertEquals(1476, romanToInt("MCDLXXVI"));
    }

    public int romanToInt(String s) {
        if (s.length() == 0) return 0;
        Integer last = null;
        int value = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int anInt = getInt(s.charAt(i));
            if (last == null) {
                last = anInt;
                value += anInt;
            } else if (anInt >= last) {
                value += anInt;
                last = null;
            } else {
                value -= anInt;
                last = null;
            }
        }
        return value;
    }

    private int getInt(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
