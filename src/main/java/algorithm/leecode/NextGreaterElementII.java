package algorithm.leecode;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 11/1/2019
 */
public class NextGreaterElementII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(test(new int[]{1, 2, 1})));
    }

    public static int[] test(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = -1;
            int i1 = i + length;
            for (int j = i + 1; j < i1; j++) {
                int num1 = nums[i];
                int num2 = nums[j % length];
                if (num1 < num2) {
                    result[i] = num2;
                    break;
                }
            }
        }
        return result;
    }
}
