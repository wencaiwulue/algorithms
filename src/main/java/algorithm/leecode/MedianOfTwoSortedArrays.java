package algorithm.leecode;

/**
 * @author fengcaiwen
 * @since 5/28/2019
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        long start = System.nanoTime();
        double v = findMedianSortedArrays(nums1, nums2);
        long end = System.nanoTime();
        System.out.printf("%s ns\n", end - start);

        System.out.println(v);
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
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

    public static double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int t = m + n;
        int[] ints = new int[n + m];
        int i = 0, j = 0;
        int v = 0;
        int v1 = 0;
        for (int p = 0; p < t; p++) {
            if (i >= m && j >= n) break;
            if (i >= m) {
                v = Integer.MAX_VALUE;
            } else if (j >= n) {
                v1 = Integer.MAX_VALUE;
            } else {
                v = nums1[i];
                v1 = nums2[j];
            }
            if (v > v1)
                ints[p] = nums2[j++];
            else
                ints[p] = nums1[i++];
        }

        double result;

        if (t % 2 == 0) {
            result = (ints[(t >> 1) - 1] + ints[t >> 1]) / 2d;
        } else {
            result = ints[t >> 1];
        }

        return result;
    }
}
