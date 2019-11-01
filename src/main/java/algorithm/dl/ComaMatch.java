package algorithm.dl;

import java.util.BitSet;
import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 10/31/2019
 */
public class ComaMatch {
    public static void main(String[] args) {
        String s = "))(((((()()()())(8)";
        run(s);
    }

    private static int run(String s) {
        String[] split = s.split("[^()]");
        int max = 0;
        for (String s1 : split) {
            max = Math.max(max, test(s1));
        }
        return max;
    }

    private static int test(String str) {
        int length = str.length();
        char[] chars = str.toCharArray();
        BitSet bitSet = new BitSet(length);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            if (chars[i] == '(' && (i + 1) < length && chars[i + 1] == ')') {
                bitSet.set(i, true);
                bitSet.set(i + 1, true);
                i++;
            } else if (chars[i] == '(') {
                stack.push(i);
            } else if (chars[i] == ')') {
                if (!stack.isEmpty()) {
                    int j = stack.pop();
                    if (j >= 0 && !bitSet.get(j) && chars[j] == '(') {
                        bitSet.set(i, true);
                        bitSet.set(j, true);
                    }
                }
            }
        }
        int max = 0;
        int l = 0;
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                l++;
                System.out.print(1);
            } else {
                max = Math.max(max, l);
                l = 0;
                System.out.print(0);
            }
        }
        max = Math.max(max, l);
        System.out.println();
        return max;
    }
}