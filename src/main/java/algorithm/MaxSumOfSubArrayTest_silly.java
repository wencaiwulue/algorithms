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
//        int[] s = new int[]{2, -1, 2, 1, 3, -2, 1, 2, 1, -2};
//        int[] s = new int[]{1, 2, 3, 4, -1, -2, -2};
//        int[] s = new int[]{-1, -2, -2, 1, 2, 3, 4};
//        int[] s = new int[]{-1, -2, -2};
//        int[] s = new int[]{-1, 0, -2};
//        int[] s = new int[]{2, -1, 2, 1, 3, -2, 1, 2, 1, -2};
//        int[] s = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] s = new int[]{1, 2, 3, 4};
        int[] s = new int[]{8, -19, 5, -4, 20};
//        int[] test = test(s);
//        int i = maxSumOfSubArray(s);
        System.out.println(Arrays.toString(preHandle(s)));
        System.out.println(preCheck(s));
        System.out.println(maxSumOfSubArray(s));
//        System.out.println(i);

    }

    private static int maxSumOfSubArray(int[] nums) {
        // [6, -39, 38, -7, 12, -27, 15, -4, 14]
        Integer integer = preCheck(nums);
        if (integer != null) return integer;

        int[] ints = preHandle(nums);

//        ints[0] > 0 ?

        int mt = ints[0];
        // 是否有间隔
        int sp = 0;
        for (int i = 1; i < ints.length - 1; i += 2) {
            if (ints[i] + ints[i + 1] > 0) {
                if (sp == 1) {
                    int temp = ints[i] + ints[i + 1];
                    if (temp > mt) {
                        mt = temp;
                        sp = 0;
                    } else {
                        sp = 1;
                    }
                } else {
                    int temp = mt + ints[i] + ints[i + 1];
                    if (temp > ints[i + 1]) {
                        mt = temp;
                        sp = 0;
                    } else {
                        mt = ints[i + 1];
                        sp = 0;
                    }
                    System.out.println("temp:" + temp);
                }
            } else {
                if (ints[i + 1] > mt) {
                    mt = ints[i + 1];
                    sp = 0;
                }
            }
            sp = 1;
            System.out.printf("%s \t ", mt);
        }

        return mt;
    }

    // 如果都是真的，如果都是负的，如果只有一个值，如果没有值
    private static Integer preCheck(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int max = Integer.MIN_VALUE;
        int t = 0;
        int pl = 0;
        for (int num : nums) {
            if (num > max) max = num;
            if (num > 0) {
                pl++;
            }
            t += num;
        }
        // 都是负的
        if (pl == 0) {
            return max;
            // 都是正的
        } else if (pl == nums.length) {
            return t;
        }
        return null;
    }

    // 将连续的正值和连续的负值，都累加起来，形成正负间隔的情况。
    private static int[] preHandle(int[] nums) {
//        if (nums == null || nums.length == 0) return new int[0];
        List<Integer> list = new ArrayList<>();
        int t = 0;
        int lastIsP = nums[0] > 0 ? 1 : 0;
        boolean first = false;
        for (int num : nums) {

            {
                // cut head negative subarray
                if (num >= 0 && !first) {
                    first = true;
                }
                if (!first) continue;
            }

            if (lastIsP == 1) {
                if (num >= 0) {
                    t += num;
                } else {
                    list.add(t);
                    t = num;
                }
            } else if (num > 0) {
                if (t != 0) {
                    list.add(t);
                }
                t = num;
            } else {
                t += num;
            }
            // todo: 这里有个疑问就是0，既不是正数也不是负数
            lastIsP = num > 0 ? 1 : num < 0 ? 0 : lastIsP;
        }
        // forget the last time
        list.add(t);

        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }

        return ints;
    }

}
