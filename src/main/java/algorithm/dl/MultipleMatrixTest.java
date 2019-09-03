package algorithm.dl;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * @author fengcaiwen
 * @since 9/2/2019
 */
public class MultipleMatrixTest {
    /*
     * find the order and calculate
     * assume:
     * m[i, j] = k, from Ai to Aj, k is best cut point, it will cut off two parts, Ai A(i+1) ... Ak, A(k+1) A(K+2)... Aj
     * s[i, j] = cost, the step of Ai * A(i+1) * ... * Aj = cost
     *
     * Ai Ai+1 ... Aj
     * assume Ak and A(k+1) is the cut point
     *
     * so:
     * s(i, k)+s(k+1, j) < s(i,j)
     * if i = j, then s(i, j)=0
     * to m , j>i make sense
     *
     */
    public static void test(ArrayList<Matrix> matrix) {
        Matrix m = new Matrix(2, matrix.size());
        Matrix s = new Matrix(2, matrix.size());
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.columns; j++) {
                m.set(i + 1, j + 1, Integer.MAX_VALUE);
                s.set(i + 1, j + 1, Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                int times = 0;
                for (int k = 0; k < j - i; k++) {
                    Matrix a = matrix.get(i);
                    Matrix b = matrix.get(j);
                    times += a.rows * a.columns * b.columns;
                }
                if (times < s.get(i, j)) {
                    s.set(i, j, times);
                    m.set(i, j, j - i);
                }
            }
        }

    }

    /*
     *
     * A(5x4) * A(4x7) = A(5x7)
     *
     */
    public static Matrix simple(Matrix a, Matrix b) {
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

    public static class Matrix {
        private int[] value;
        private int rows;
        private int columns;

        public Matrix(int[] ints, int rows, int columns) {
            assertEquals(rows * columns, ints.length);
            this.value = ints;
            this.rows = rows;
            this.columns = columns;
        }

        public Matrix(int rows, int columns) {
            this.value = new int[rows * columns];
            this.rows = rows;
            this.columns = columns;
        }

        public int get(int x, int y) {
            return this.value[(x - 1) * columns + (y - 1)];
        }

        public void set(int x, int y, int value) {
            this.value[(x - 1) * columns + (y - 1)] = value;
        }

        public int getRows() {
            return rows;
        }

        public int getColumns() {
            return columns;
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
    }

    public static void main(String[] args) {
        int[] ints = IntStream.range(1, 9).toArray();
        System.out.println(ints.length);
        Matrix a = new Matrix(ints, 2, 4);
        Matrix b = new Matrix(IntStream.range(1, 13).toArray(), 4, 3);
        Matrix c = simple(a, b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

}
