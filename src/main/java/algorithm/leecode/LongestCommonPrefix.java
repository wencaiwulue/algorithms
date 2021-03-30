package algorithm.leecode;

import org.junit.Test;

public class LongestCommonPrefix {

    @Test
    public void longestCommonPrefix() {
        String prefix = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        System.out.println("prefix = " + prefix);
        prefix = longestCommonPrefix(new String[]{"dog", "racecar", "car"});
        System.out.println("prefix = " + prefix);
        prefix = longestCommonPrefix(new String[]{"ab", "a"});
        System.out.println("prefix = " + prefix);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int to = 0;
        int i = strs[0].length();
        out:
        for (int j = 0; j < i; j++) {
            Character c = null;
            for (String str : strs) {
                if (j >= str.length()) {
                    break out;
                }
                if (c == null) {
                    c = str.charAt(j);
                } else {
                    if (str.charAt(j) != c) {
                        break out;
                    }
                }
            }
            to++;
        }
        return strs[0].substring(0, to);
    }
}
