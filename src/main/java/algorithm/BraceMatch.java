package algorithm;

import java.util.Stack;

/**
 * @author fengcaiwen
 * @since 8/5/2019
 */
public class BraceMatch {

    public static void main(String[] args) {
        String s = "{}{}{}{}]";
        System.out.println(match(s));
    }

    private static boolean match(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (aChar == '{' || aChar == '(' || aChar == '[') {
                stack.push(aChar);
            } else if (aChar == '}' || aChar == ')' || aChar == ']') {
                if (stack.isEmpty()) return false;
                else stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
