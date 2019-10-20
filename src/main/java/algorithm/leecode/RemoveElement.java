package algorithm.leecode;

/**
 * @author fengcaiwen
 * @since 9/29/2019
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] ints = new int[]{3, 3}; //3 3 2 2
        int num = 2;
        System.out.println(removeElement(ints, num));
    }

    private static int removeElement(int[] nums, int val) {
        int length = nums.length;
        int p = 0;
        for (int i = 0, j = length - 1; i < length && j > i; ) {
            while (nums[i] != val && (i++ < (length - 1))) {
            }
            while (nums[j] == val && (j-- >= 0)) {
            }
            if (j < i) continue;

            nums[i] = nums[j];
            nums[j] = val;
            i++;
            j--;
        }

        for (int num : nums) {
            if (num == val) break;
            else p++;
        }
        return p;
    }
}
