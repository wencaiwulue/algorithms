package algorithm.leecode;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author fengcaiwen
 * @since 5/22/2019
 */
public class TwoSum {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(null);
        list.add(1);

        System.out.println(list.size());
        System.out.println(list.toString());

        int t = 6;
        int[] nums = new int[]{3, 2, 4,123123,3,123,3,23,3,32,23,4,5,6,7,4,55,54,4,4,3,43,43};
        long s = System.nanoTime();
        System.out.println(Arrays.toString(getIndex(nums, t)));
        System.out.printf("%sms\n", TimeUnit.NANOSECONDS.toNanos(System.nanoTime() - s));
        long ss = System.nanoTime();
        System.out.println(Arrays.toString(get(nums, t)));

        System.out.printf("%sms\n", TimeUnit.NANOSECONDS.toNanos(System.nanoTime() - ss));

    }

    private static int[] getIndex(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> indexMap = new HashMap<>(length);

        for (int i = 0; i < length; i++)
            indexMap.put(nums[i], i);

        for (int i = 0; i < length; i++) {
            if (nums[i] + nums[i] == target) continue;
            int i1 = target - nums[i];
            if (indexMap.containsKey(i1)) {
                return new int[]{i, indexMap.get(i1)};
            }
        }
        return new int[]{-1, -1};
    }

    private static int[] get(int[] nums, int target) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        BitSet bitSet = new BitSet(max);
        if (min < 0) {
            bitSet = new BitSet(max - min);
        }
        for (int i : nums) {
            bitSet.set(Math.abs(i), true);
        }
        int i1 = -1, i2 = -1;

//        if (min < 0) {
//            for (int num : nums) {
//
//            }
//        }
        out:
        for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
            int num = nums[i];
            if (bitSet.get(target - num)) {
                i1 = i;
                for (int i3 = 0; i3 < nums.length; i3++) {
                    if (nums[i3] == target - num) {
                        if (i3 == i1) {
                            break;
                        } else {
                            i2 = i3;
                            break out;
                        }
                    }
                }
            }
        }
        return new int[]{i1, i2};

    }
}
