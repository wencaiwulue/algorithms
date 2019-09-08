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
    public static Matrix test(ArrayList<Matrix> matrices) {

        // init two matrix, fill full with Integer.MAX_VALUE
        Matrix s = new Matrix(matrices.size(), matrices.size());
        Matrix m = new Matrix(matrices.size(), matrices.size());
        for (int i = 0; i < s.rows; i++) {
            for (int j = 0; j < s.columns; j++) {
//                s.set(i + 1, j + 1, Integer.MAX_VALUE);
//                m.set(i + 1, j + 1, Integer.MAX_VALUE);
            }
        }

        for (int i = 1; i < matrices.size() + 1; i++) {
            for (int j = 1; j < matrices.size() + 1; j++) {
                if (j < i) continue;
                for (int k = i; k <= j; k++) {
                    int partA = 0;
                    int partB = 0;
                    Matrix c = matrices.get(i - 1);
                    for (int l = i + 1; l <= k; l++) {
                        Matrix a = matrices.get(l - 1);
                        partA += c.rows * c.columns * a.columns;
                        c = new Matrix(c.rows, a.columns);
                    }

                    Matrix d = matrices.get(k - 1);
                    for (int l = k + 1 + 1; l <= j; l++) {
                        Matrix b = matrices.get(l - 1);
                        partB += d.rows * d.columns * b.columns;
                        d = new Matrix(d.rows, b.columns);
                    }

                    int increment = c.rows * c.columns * d.columns;

                    int pa = matrices.get(i - 1).rows * matrices.get(k - 1).columns * matrices.get(j - 1).columns;

                    // if k is best cut point, it should: m(i, k) + m(k+1, j) < m(i, j), otherwise, do nothing
                    if (/*partA + partB + increment*/pa + m.get(i, k) + m.get(k + 1, j) < m.get(i, j)) {
                        m.set(i, j, partA + partB + increment);
                        s.set(i, j, k);
                    }
                }
            }
        }
//        System.out.println(m);
//        System.out.println(s);
        return s;
    }

    /*
     * excellent, use system stack to calculate
     */
    public static Matrix calculate(ArrayList<Matrix> matrices, Matrix m, int from, int to) {
        if (from + 1 == to) {
            return matrices.get(from - 1).multiply(matrices.get(to - 1));
        } else if (from == to) {
            return matrices.get(from - 1);
        }

        int i = m.get(from, to);
        Matrix a = calculate(matrices, m, from, i);
        Matrix b = calculate(matrices, m, i + 1, to);
        return a.multiply(b);
    }

    /*
     * expression
     *
     */
    public static String calculatePath(ArrayList<Matrix> matrices, Matrix m, int from, int to) {
        if (from + 1 == to) {
            return String.format("(A%s*A%s)", from, to);
        } else if (from == to) {
            return String.format("A%s", from);
        }

        int i = m.get(from, to);
        String a = calculatePath(matrices, m, from, i);
        String b = calculatePath(matrices, m, i + 1, to);
        return String.format("(%s)*(%s)", a, b);
    }

    public static void calculatePathV2(Matrix s, int i, int j) {
        if (i == j) {
            System.out.printf("A(%s)", i);
        } else {
            System.out.println("(");
            calculatePathV2(s, i, s.get(i, j));
            calculatePathV2(s, s.get(i, j) + 1, j);
            System.out.println(")");
        }
    }

    public static int calculatePathStep(ArrayList<Matrix> matrices, Matrix m, int from, int to) {
        if (from + 1 == to) {
            Matrix a = matrices.get(from - 1);
            Matrix b = matrices.get(to - 1);
            return a.rows * a.columns * b.columns;
        } else if (from == to) {
            return 0;
        }

        int i = m.get(from, to);
        int a = calculatePathStep(matrices, m, from, i);
        Matrix am = calculate(matrices, m, from, i);
        int b = calculatePathStep(matrices, m, i + 1, to);
        Matrix bm = calculate(matrices, m, i + 1, to);
        int increment = am.rows * am.columns * bm.columns;

        return a + b + increment;
    }


    /*
     *
     * A(5x4) * A(4x7) = A(5x7)
     *
     */
    public static Matrix simpleMultiply(Matrix a, Matrix b) {
        assertEquals(a.columns, b.rows);
        Matrix c = new Matrix(a.rows, b.columns);
        for (int i = 1; i < a.rows + 1; i++) {
            for (int j = 1; j < b.columns + 1; j++) {
                int value = 0;
                for (int k = 1; k < a.columns + 1; k++)
                    value += a.get(i, k) * b.get(k, j);
                c.set(i, j, value);
            }
        }
        return c;
    }

    public static Matrix matrixChainOrder(ArrayList<Integer> matrices) {
        int n = matrices.size();
        Matrix s = new Matrix(matrices.size(), matrices.size());
        Matrix m = new Matrix(matrices.size(), matrices.size());
        for (int i = 1; i <= n; i++)
            m.set(i, i, 0);
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m.set(i, j, Integer.MAX_VALUE);
                for (int k = i; k <= j - 1; k++) {
                    int q = m.get(i, k) + m.get(k + 1, j) + matrices.get(i - 1) * matrices.get(k - 1) * matrices.get(j - 1);
                    if (q < m.get(i, j)) {
                        m.set(i, j, q);
                        s.set(i, j, k);
                    }
                }
            }
        }
        return s;
    }

    public static class Matrix {
        // multiple matrix actually can express as a array
        private int[][] value;
        // the rows of matrix
        private int rows;
        // the columns of matrix
        private int columns;

        public Matrix(int[] ints, int rows, int columns) {
            assertEquals(ints.length, rows * columns);
            this.value = null;
            this.rows = rows;
            this.columns = columns;
        }

        public Matrix(int rows, int columns) {
            this.value = new int[rows + 1][columns + 1];
            this.rows = rows;
            this.columns = columns;
        }

        public int get(int x, int y) {
            return this.value[x][y];
        }

        public void set(int x, int y, int value) {
            this.value[x][y] = value;
        }

        public int getRows() {
            return rows;
        }

        public int getColumns() {
            return columns;
        }

        public Matrix multiply(Matrix b) {
            return simpleMultiply(this, b);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < rows + 1; i++) {
                for (int j = 1; j < columns + 1; j++) {
                    sb.append(String.format("(%s, %s) = %s\n", i, j, get(i, j)));
                }
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Matrix matrix = (Matrix) o;
            return rows == matrix.rows &&
                    columns == matrix.columns &&
                    Arrays.equals(value, matrix.value);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(rows, columns);
            result = 31 * result + Arrays.hashCode(value);
            return result;
        }
    }

    public static void main(String[] args) {
        Matrix a = new Matrix(IntStream.range(0, 30 * 35).toArray(), 30, 35);
        Matrix b = new Matrix(IntStream.range(0, 35 * 15).toArray(), 35, 15);
        Matrix c = new Matrix(IntStream.range(0, 15 * 5).toArray(), 15, 5);
        Matrix d = new Matrix(IntStream.range(0, 5 * 10).toArray(), 5, 10);
        Matrix e = new Matrix(IntStream.range(0, 10 * 20).toArray(), 10, 20);
        Matrix f = new Matrix(IntStream.range(0, 20 * 25).toArray(), 20, 25);
        ArrayList<Matrix> matrices = new ArrayList<>(Arrays.asList(a, b, c, d, e, f));
//        Matrix m = test(matrices);
//        System.out.println(m);
//        for (int i = 0; i < matrices.size(); i++) {
//            System.out.println(matrices.get(i));
//        }
//        Matrix result1 = a.multiply(b).multiply(c).multiply(d);
//        System.out.println();
//        Matrix result2 = calculate(matrices, m, 1, m.rows);
//        String s = calculatePath(matrices, m, 1, m.rows);
//        System.out.println(s);
//        System.out.println(calculatePathStep(matrices, m, 1, m.rows));
//        System.out.println("v2");
//        System.out.println(matrixChainOrder(new ArrayList<>(Arrays.asList(30, 35, 15, 5, 10, 20, 25))));
//        calculatePathV2(m, 1, 6);

//        System.out.println(result1);
//        System.out.println(result2);
//        System.out.println(result1.equals(result2));
        int[][] aa = new int[][]{{3, 4, 5}, {1, 2, 3}};
        System.out.println(aa.length);
        System.out.println(aa[0].length);
    }

}
