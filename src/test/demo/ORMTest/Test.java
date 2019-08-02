package demo.ORMTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 7/31/2019
 */
public class Test {
    public static void main(String[] args) {
        int[] s = new int[]{-3, 6, 0, -16, 0, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7, 0, 7};
//        int[] s = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] s = new int[]{2, -1, 2, 1, 3, -2, 1, 2, 1, -2};
//        int[] test = test(s);
        int i = maxSubArray(s);
        System.out.println(Arrays.toString(test(s)));
        System.out.println(i);

    }

    public static int maxSubArray(int[] nums) {
//        [6, -39, 38, -7, 12, -27, 15, -4, 14]

        if (nums.length == 1) return nums[0];
        int max = Integer.MIN_VALUE;
        int allN = 0;
        for (int num : nums) {
            max = max < num ? num : max;
            if (num <= 0) {
                allN++;
            }
        }
        if (max < 0 || allN == nums.length) {
            return max;
        }

        int[] test = test(nums);
        int mt = test[0];
        for (int i = 0; i < test.length - 2; i += 2) {
            if (test[i] > 0) {
                if (test[i + 1] + test[i + 2] > 0) {
                    if (mt + test[i + 1] > 0) {
                        int to = mt + test[i + 1] + test[i + 2];
                        mt = mt < to ? to : mt;
                    } else {
                        int to = test[i + 2];
                        mt = mt < to ? to : mt;
                    }
                } else {
                    int to = test[i];
                    mt = mt < to ? to : mt;
                }
            }
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
        result.add(0);
        int[] a = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            a[i] = result.get(i);
        }

        return a;
    }
}
