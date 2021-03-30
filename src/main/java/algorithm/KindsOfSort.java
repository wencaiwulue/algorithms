package algorithm;

import org.junit.Test;

import java.util.Arrays;

public class KindsOfSort {
    @Test
    public void heapSort() {
        int[] ints = new int[]{4, 5, 10, 2, 3, 1};
        heapSort(ints, ints.length);
        System.out.println(Arrays.toString(ints));
    }

    public void heapSort(int[] tree, int n) {
        buildHeap(tree, n);
        for (int i = n - 1; i >= 0; i--) {
            swap(tree, i, 0);
            heapify(tree, i, 0);
        }
    }

    public void buildHeap(int[] tree, int n) {
        int lastNode = n - 1;
        int p = (lastNode - 1) / 2;
        for (int i = p; i >= 0; i--) {
            heapify(tree, n, i);
        }
    }

    public void heapify(int[] tree, int n, int i) {
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;
        int max = i;
        if (c1 <= n - 1 && tree[c1] > tree[max]) {
            max = c1;
        }
        if (c2 <= n - 1 && tree[c2] > tree[max]) {
            max = c2;
        }
        if (max != i) {
            swap(tree, max, i);
            heapify(tree, n, max);
        }
    }

    public void swap(int[] tree, int x, int y) {
        int temp = tree[x];
        tree[x] = tree[y];
        tree[y] = temp;
    }

    @Test
    public void quickSort() {
        int[] ints = new int[]{4, 5, 10, 2, 3, 1};
        quickSort(ints, ints.length);
        System.out.println(Arrays.toString(ints));
    }

    public void quickSort(int[] ints, int n) {
        quickSort(ints, 0, n);
    }

    public void quickSort(int[] ints, int from, int to) {
        if (from >= to) {
            return;
        }
        int e = ints[from];

        int i, j;
        for (i = from, j = to - 1; i < j; ) {
            if (ints[i] > e && ints[j] < e) {
                swap(ints, i, j);
                i++;
                j--;
            } else if (ints[i] > e) {
                j--;
            } else if (ints[j] < e) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        swap(ints, from, i);
        quickSort(ints, from, i);
        quickSort(ints, i + 1, to);
    }

    @Test
    public void bobbleSort() {
        int[] ints = new int[]{10, 4, 5, 1, 2, 7};
        bobbleSort(ints, ints.length);
        System.out.println(Arrays.toString(ints));
    }

    public void bobbleSort(int[] ints, int n) {
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < n - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    swap(ints, j, j + 1);
                }
            }
        }
    }
}
