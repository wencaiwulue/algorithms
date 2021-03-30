package algorithm.leecode;

import org.junit.Test;

import java.util.Arrays;

public class LcsLength {
    @Test
    public void lcs_length() {
        String a = "ABCBDAB";
        String b = "BDCABA";
        lcs_length(a.toCharArray(), b.toCharArray());
    }

    public void lcs_length(char[] x, char[] y) {
        int m = x.length;
        int n = y.length;
        String[][] b = new String[m + 1][n + 1];
        int[][] c = new int[m + 1][n + 1];
        for (int i = 1; i < m; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            c[0][j] = 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (x[i] == y[j]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = "↖";
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = "↑";
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = "←";
                }
            }
        }
        for (String[] strings : b) {
            System.out.println(Arrays.deepToString(strings));
        }
        for (int[] ints : c) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
