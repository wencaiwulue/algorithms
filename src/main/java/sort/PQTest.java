package sort;

/**
 * priority queue
 *
 * @author fengcaiwen
 * @since 7/12/2019
 */
public class PQTest<T extends Comparable<T>> {

    private int N;
    private T[] pq;

    public PQTest(int N) {
        pq = (T[]) new Comparable[N + 1];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T t) {
        pq[++N] = t;
        swim(N);
    }

    public T delMax() {
        T max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    public T delMin() {
        T min = pq[N];
        pq[N--] = null;
        return min;
    }

    public T del(T t) {
        int i = indexOf(t);
        if (i < 0) return t;

        exch(i, N--);
        pq[N + 1] = null;
        sink(i);
        return t;
    }


    private int indexOf(T t) {
        return indexOf0(t, 1);
    }

    private int indexOf0(T t, int i) {
        int i1 = pq[i].compareTo(t);
        if (i1 == 0) return i;
        if (i1 < 0) return -1;

        int i2 = indexOf0(t, i * 2);
        int i3 = indexOf0(t, i * 2 + 1);
        if (i2 < 0 && i3 < 0) return -1;
        if (i2 > 0) return i2;
        if (i3 > 0) return i3;
        return -1;
    }
}