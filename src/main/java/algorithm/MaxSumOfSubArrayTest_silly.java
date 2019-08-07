package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 8/5/2019
 */
public class MaxSumOfSubArrayTest_silly {
    public static void main(String[] args) {
//        int[] s = new int[]{-3, 6, 78, -16, 0, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7, 0, 7};
//        int[] s = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] s = new int[]{2, -1, 2, 1, 3, -2, 1, 2, 1, -2};
//        int[] s = new int[]{1, 2, 3, 4, -1, -2, -2};
//        int[] s = new int[]{-1, -2, -2, 1, 2, 3, 4};
//        int[] s = new int[]{-1, -2, -2};
//        int[] s = new int[]{-1, 0};
//        int[] s = new int[]{2, -1, 2, 1, 3, -2, 1, 2, 1, -2};
//        int[] s = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] test = test(s);
        int i = maxSumOfSubArray(s);
        System.out.println(Arrays.toString(test(s)));
        System.out.println(i);

    }

    private static int maxSumOfSubArray(int[] nums) {
//        [6, -39, 38, -7, 12, -27, 15, -4, 14]

        if (nums.length == 1) return nums[0];
        int max = Integer.MIN_VALUE;
        int allN = 0;
        int allp = 0;
        int t = 0;
        for (int num : nums) {
            max = max < num ? num : max;
            if (num <= 0) {
                allN++;
            } else {
                allp++;
            }
            t += num;
        }
        if (max < 0 || allN == nums.length) {
            return max;
        } else if (allp == nums.length) {
            return t;
        }

        int[] test = test(nums);
        int mt = test[0];
        int sp = 0;
        for (int i = 1; i < test.length - 1; i += 2) {
            if (test[i] + test[i + 1] > 0) {

                if (sp == 1) {
                    int temp = test[i] + test[i + 1];
                    if (temp > mt) {
                        mt = temp;
                        sp = 0;
                    } else {
                        sp = 1;
                    }
                } else {
                    int temp = mt + test[i] + test[i + 1];
                    if (temp > test[i + 1]) {
                        mt = temp;
                        sp = 0;
                    } else {
                        mt = test[i + 1];
                        sp = 0;
                    }
                    System.out.println("temp:" + temp);
                }
            } else {
                if (test[i + 1] > mt) {
                    mt = test[i + 1];
                    sp = 0;
                }
            }
            System.out.printf("%s \t ", mt);
        }

        return mt;
    }

    private static int[] test(int[] nums) {
        List<Integer> result = new ArrayList<>();
        boolean first = false;
        int t = 0;
        int lastIsP = -1;
        for (int num : nums) {
            // cut head negative subarray
            if (num > 0 && !first) {
                first = true;
            }
            if (!first) continue;


            if (lastIsP == 1) {
                if (num > 0) {
                    t += num;
                } else if (num < 0) {
                    if (t != 0) {
                        result.add(t);
                    }
                    t = num;
                }
            } else if (num > 0) {
                if (t != 0) {
                    result.add(t);
                }
                t = num;
            } else if (num < 0) {
                t += num;
            }
            lastIsP = num > 0 ? 1 : num < 0 ? 0 : lastIsP;
        }
        // forget the last time
        if (t != 0) {
            result.add(t);
        }
        result.add(0);
        int[] a = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            a[i] = result.get(i);
        }

        return a;
    }

}
