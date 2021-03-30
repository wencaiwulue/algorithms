package algorithm.leecode;

/**
 * @author fengcaiwen
 * @since 5/28/2019
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4};
        int[] nums2 = new int[]{2, 3};
        double v = findMedianSortedArrays_v1(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1);
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

    public static double findMedianSortedArrays_v1(int[] nums1, int[] nums2, int i1, int i2, int j1, int j2) {
        if (i2 - i1 == 0 && j2 - j1 == 0) {
            return (nums1[i2] + nums2[j1]) / 2D;
        } else if (i2 - i1 == 0 || j2 - j1 == 0) {
            if (i2 - i1 == 0) {
                if ((j2 - j1 + 1) % 2 == 0) {
                    double a = nums2[(j2 - j1 + 1) / 2 + j1];
                    double b = nums2[(j2 - j1 + 1) / 2 + 1 + j1];
                    return (a + b) / 2D;
                } else {
                    return nums2[(j2 - j1 + 1) / 2 + j1];
                }
            } else {
                if ((i2 - i1 + 1) % 2 == 0) {
                    double a = nums1[(i2 - i1 + 1) / 2 + i1];
                    double b = nums1[(i2 - i1 + 1) / 2 + 1 + i1];
                    return (a + b) / 2;
                } else {
                    return nums2[(i2 - i1 + 1) / 2 + i1];
                }
            }
        }

        int m, n;
        m = nums1[i1] <= nums2[j1] ? i1 : j1;
        n = nums1[i2] >= nums2[j2] ? i2 : j2;
        if (m == i1) {
            i1++;
        } else {
            j1++;
        }
        if (n == i2) {
            i2--;
        } else {
            j2--;
        }

        return findMedianSortedArrays_v1(nums1, nums2, i1, i2, j1, j2);
    }
}
