package algorithm;

import java.util.Arrays;

/**
 * @author fengcaiwen
 * @since 8/5/2019
 */
public class MaxSubArrayTest {

    public static void main(String[] args) {
//        int[] ints = new int[]{7, 5, 2, 4, 6, 1, 3};
//        int[] ints = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] ints = new int[]{-3, -25, 20, 20, 10, -3, 6, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        maxSumOfNumSet(ints);
    }


    private static int[] maxSumOfNumSet(int[] nums) {
        int start = 0;
        int lastIsP = nums[0] > 0 ? 1 : 0;
        int pl = 0;
        int nl = 0;
        int ns = 0;
        int ps = 0;
        int total = nums[0];
        int totalMax = 0;
        int resultStart = 0;
        int resultEnd = 0;
        int leftP = 0;
        int leftPl = 0;
        int leftN = 0;
        int leftNl = 0;
        for (int i = 1; i < nums.length; i++) {
            boolean b = nums[i] > 0;
            if (b) {
                // p -> p
                if (lastIsP == 1) {
                    pl++;
                    ps += nums[i];
                    total += nums[i];
                } else {
                    leftN = ns;
                    // n->p
                    pl = 1;
                    ps = nums[i];
                    total += nums[i];
                }
            } else {
                // from p -> n
                leftP = ps;
                if (lastIsP == 1) {
                    // valuable
                    if ((ps + ns) > 0) {
                        ps = 0;
                        ns = nums[i];
                        pl = 0;
                        nl = 1;
                        total += nums[i];
                    } else {

                        {
                            if (totalMax < (ps + nums[i])) {
                                totalMax = ps + nums[i];
                                resultStart = start;
                                resultEnd = (i - nl - pl);
                            }
                            if (leftP + leftN < 0) {
                                total += -(leftN + lastIsP);
                                resultStart = start;
                                resultEnd = (i - nl - pl);
                            }
                        }

                        // no valuable
                        total = ps + nums[i];
                        start = i - pl;
                        ps = 0;
                        ns = nums[i];
                        pl = 0;
                        nl = 1;
                    }
                    // from n -> n
                } else {
                    nl++;
                    ns += nums[i];
                    total += nums[i];
                }
            }
            lastIsP = b ? 1 : 0;
        }

        System.out.println(totalMax);
        System.out.println(Arrays.toString(nums));
        System.out.println(String.format("from: %s ~ %s", resultStart, resultEnd));
        return null;
    }
}
