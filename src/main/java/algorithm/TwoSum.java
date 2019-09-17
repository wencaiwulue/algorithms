package algorithm;

import java.util.*;

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
        int[] nums = new int[]{3, 2, 4};
        System.out.println(Arrays.toString(getIndex(nums, t)));
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
}
