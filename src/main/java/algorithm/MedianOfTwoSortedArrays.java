package algorithm;

/**
 * @author fengcaiwen
 * @since 5/28/2019
 */
public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] ints = new int[n + m];
        int i = 0, j = 0;
        int po = 0;
        for (int p = 0; p < (m + n); p++) {
            po = p;
            if (i >= m || j >= n) break;
            if (nums1[i] > nums2[j])
                ints[p] = nums2[j++];
            else
                ints[p] = nums1[i++];
        }

        if (i != m) {
            System.arraycopy(nums1, i, ints, po, m - i);
        } else if (j != n) {
            System.arraycopy(nums2, j, ints, po, n - j);
        }

        double result;

        if ((m + n) % 2 == 0) {
            result = (ints[(m + n) / 2 - 1] + ints[(n + m) / 2]) / 2d;
        } else {
            result = ints[(m + n) / 2];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
