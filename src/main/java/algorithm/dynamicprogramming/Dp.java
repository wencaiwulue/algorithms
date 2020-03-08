package algorithm.dynamicprogramming;

/**
 * @author naison
 * @since 2/27/2020 14:55
 */
public class Dp {
    private int cap; // 总重
    private int[] w;
    private int n;//物品个数
    private int[] v;
    private int[][] op;//到i,j的最优路径

    private Dp(int cap, int[] w, int[] v) {
        this.cap = cap;
        this.n = w.length - 1;
        this.w = w;
        this.v = v;
        this.op = new int[w.length][cap + 1];
    }

    public void test() {
        for (int j = 0; j <= cap; j++) {
            op[0][j] = 0;
        }

        //i从1开始,j从0开始
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= cap; j++) {
                if (j < w[i]) {
                    //不可以
                    op[i][j] = op[i - 1][j];
                } else {
                    //可以放
                    op[i][j] = Math.max(op[i - 1][j - w[i]] + v[i], op[i - 1][j]);
                }
            }
        }

        boolean[] ok = new boolean[n + 1];
        for (int i = n; i >= 1; i--) {
            if (op[i][cap] == op[i - 1][cap])
                ok[i] = false;
            else {
                ok[i] = true;
                cap -= w[i];
            }
        }

        System.out.println("要拿的物品号为：");
        for (int i = 1; i <= n; i++) {
            if (ok[i]) {
                System.out.print(i + " ");
            }
        }
    }


    public static void main(String[] args) {
        int[] v = {0, 1, 2, 3, 4};//第零个不算
        int[] w = {0, 4, 3, 2, 1};//第零个不算
        Dp test = new Dp(4, v, w);
        test.test();
    }
}

