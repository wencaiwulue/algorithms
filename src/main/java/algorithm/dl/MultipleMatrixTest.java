package algorithm.dl;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * find the best way to calculate multiple matrix
 *
 * @author fengcaiwen
 * @since 9/2/2019
 */
public class MultipleMatrixTest {
    /*
     * find the order and calculate
     *
     * assume you have know knowledge about matrix, otherwise, please google/bing
     *
     * assume:
     * s[i, j] = k, from Ai to Aj, k is best cut point, it will cut off two parts, Ai A(i+1) ... Ak, A(k+1) A(K+2)... Aj
     * m[i, j] = cost, the step of Ai * A(i+1) * ... * Aj = cost
     *
     * Ai Ai+1 ... Aj
     * assume Ak and A(k+1) is the cut point
     *
     * so:
     * if i = j, m[i,j]=0
     * if i < j, m[i,j]=min{m[i,k] + m[k+1, j] + p(i-1)p(k)p(j)}
     *
     * to m , j>i make sense
     *
     */
    public static int memory(int[][] matrices, int m, int n) {
        if (m == n) {
            return 0;
        }
        if (m < n) {
            return 0;
        } else {
            if (matrices[m][n] == Integer.MAX_VALUE) {

            }
        }


        return -1;
    }

    /*
     * excellent, use system stack to calculate
     */
    public static int[][] calculate(ArrayList<int[][]> matrices, int[][] m, int from, int to) {
        if (from + 1 == to) {
            return simpleMultiply(matrices.get(from - 1), (matrices.get(to - 1)));
        } else if (from == to) {
            return matrices.get(from - 1);
        }

        int i = m[from][to];
        int[][] a = calculate(matrices, m, from, i);
        int[][] b = calculate(matrices, m, i + 1, to);
        return simpleMultiply(a, b);
    }

    /*
     * expression
     *
     */
    public static String calculatePath(ArrayList<int[][]> matrices, int[][] m, int from, int to) {
        if (from + 1 == to) {
            return String.format("(A%s*A%s)", from, to);
        } else if (from == to) {
            return String.format("A%s", from);
        }

        int i = m[from][to];
        String a = calculatePath(matrices, m, from, i);
        String b = calculatePath(matrices, m, i + 1, to);
        return String.format("(%s)*(%s)", a, b);
    }

    public static void calculatePathV2(int[][] s, int i, int j) {
        if (i == j) {
            System.out.printf("A(%s)", i);
        } else {
            System.out.print("(");
            calculatePathV2(s, i, s[i][j]);
            calculatePathV2(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static int calculatePathStep(ArrayList<int[][]> matrices, int[][] m, int from, int to) {
        if (from + 1 == to) {
            int[][] a = matrices.get(from - 1);
            int[][] b = matrices.get(to - 1);
            return a.length * a[0].length * b[0].length;
        } else if (from == to) {
            return 0;
        }

        int i = m[from][to];
        int a = calculatePathStep(matrices, m, from, i);
        int[][] am = calculate(matrices, m, from, i);

        int b = calculatePathStep(matrices, m, i + 1, to);
        int[][] bm = calculate(matrices, m, i + 1, to);
        int increment = am.length * am[0].length * bm[0].length;

        return a + b + increment;
    }


    /*
     *
     * A(5x4) * A(4x7) = A(5x7)
     *
     */
    public static int[][] simpleMultiply(int[][] a, int[][] b) {
        assertEquals(a[0].length, b.length);
        int r = a.length;
        int middle = b.length;
        int c = b[0].length;
        int[][] result = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int value = 0;
                for (int k = 0; k < middle; k++)
                    value += a[i][k] * b[k][j];
                result[i][j] = value;
            }
        }
        return result;
    }

    public static int[][] matrixChainOrder(ArrayList<Integer> p) {
        int length = p.size();
        int n = length - 1;
        int[][] s = new int[length][length];
        int[][] m = new int[length][length];

        for (int i = 1; i <= n; i++)
            s[i][i] = 0;
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p.get(i - 1) * p.get(k) * p.get(j);
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        {
            for (int i = 0; i < m.length - 1; i++) {
                for (int i1 = 0; i1 < m[i].length - 1; i1++) {
                    System.out.printf("%-8s", m[i][i1]);
                }
                System.out.print("\n");
            }
        }
        return s;
    }

    public static int[][] generateMatrix(int[] ints, int rows, int columns) {
        int[][] value = new int[rows][columns];
        for (int i = 0; i < ints.length; i++) {
            int r = i / columns;
            int c = i % columns;
            value[r][c] = ints[i];
        }
        return value;
    }

    public static String arrayToString(int[][] ints) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                sb.append(String.format("%-10s", ints[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        int[][] a1 = generateMatrix(IntStream.range(0, 2 * 3).toArray(), 2, 3);
        int[][] a2 = generateMatrix(IntStream.range(0, 3 * 4).toArray(), 3, 4);
        simpleMultiply(a1, a2);

        int[][] a = generateMatrix(IntStream.range(0, 30 * 35).toArray(), 30, 35);
        int[][] b = generateMatrix(IntStream.range(0, 35 * 15).toArray(), 35, 15);
        int[][] c = generateMatrix(IntStream.range(0, 15 * 5).toArray(), 15, 5);
        int[][] d = generateMatrix(IntStream.range(0, 5 * 10).toArray(), 5, 10);
        int[][] e = generateMatrix(IntStream.range(0, 10 * 20).toArray(), 10, 20);
        int[][] f = generateMatrix(IntStream.range(0, 20 * 25).toArray(), 20, 25);
        ArrayList<int[][]> matrices = new ArrayList<>(Arrays.asList(a, b, c, d, e, f));

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(30, 35, 15, 5, 10, 20, 25));
        int[][] ints = matrixChainOrder(list);
        for (int i = 0; i < ints.length; i++) {
            for (int i1 = 0; i1 < ints[i].length; i1++) {
                System.out.print(ints[i][i1] + "\t");
            }
            System.out.print("\n");
        }
        calculatePathV2(ints, 1, list.size() - 1);

        System.out.println();
        int[][] matrix = calculate(matrices, ints, 1, 6);
        System.out.println(arrayToString(matrix));


    }

}
