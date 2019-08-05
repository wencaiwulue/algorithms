package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fengcaiwen
 * @since 5/22/2019
 */
public class TwoSum {
    public static void main(String[] args) {
        int t = 6;
        int[] nums = new int[]{3, 2, 4};
        System.out.println(Arrays.toString(getIndex(nums, t)));
    }

    private static int[] getIndex(int[] nums, int target) {
        Map<Integer, Integer> differenceMap = new HashMap<>();
        Map<Integer, Integer> indexList = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            indexList.put(nums[i], i);
            int difference = target - nums[i];
            if (!differenceMap.containsKey(difference)) {
                differenceMap.put(difference, i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (differenceMap.containsKey(nums[i])) {
                if (differenceMap.get(nums[i]) == i) {
                    continue;
                }
                return new int[]{indexList.get(nums[i]), differenceMap.get(nums[i])};
            }
        }
        return new int[]{-1, -1};
    }
}
