package algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 有一辆小船，能够承载的最高重量为c，当船承载的重量超过c时，船会沉没。
 * 现在有n个物品，物品i的重量为w(i),价值为v(i)，应该如何选择装船的物品，保证船不沉，使得装上船的物品总价值最大?
 *
 * @author naison
 * @since 3/18/2020 16:07
 */
public class ZeroOnePirate {
    private int c;
    private int n;
    private int[] w;
    private int[] v;

    private int[][] f;// f(x,y)表示在总重限制为x时，选取第y个物品，可以达到的最大价值。这里的选取条件是加入第y个物品，容量不超限制，且取得的价值最大。
    // 如果拿了第i个物品，那么相应的价值f(xxx, i)会比f(yyy, i-1)大，如果不变，说明不取第i个物品（这里假设的前提是物品的价值为正整数）
    // 这样就可以求出到底拿了哪些物品，以及价值的最大值。(最大值一定处于f矩阵的最右下角)

    private ZeroOnePirate(int c, int[] w, int[] v) {
        this.c = c;
        this.n = w.length;
        this.w = new int[n + 1];
        this.w[0] = 0;
        System.arraycopy(w, 0, this.w, 1, n);// 使用第1为做为数组的开始
        this.v = new int[n + 1];
        this.v[0] = 0;
        System.arraycopy(v, 0, this.v, 1, n);// 使用第1为做为数组的开始
        this.f = new int[c + 1][n + 1]; //这里也可以是反的
        for (int[] ints : this.f) {
            for (int anInt : ints) {
                anInt = Integer.MIN_VALUE;// 全部初始化为最小值
            }
        }

        for (int i = 0; i < this.n + 1; i++) {
            this.f[0][i] = 0; //容量限制为0的情况下，最大价值总为0
        }
    }

    public void result() {
        for (int cc = 1; cc < c + 1; cc++) {//计算第nn件物品在每个容量限制下能取得的最大价值
            for (int nn = 1; nn < n + 1; nn++) {
                if (cc >= w[nn]) {// 当前容量cc是否可以存放第nn个物品
                    f[cc][nn] = Math.max(f[cc - w[nn]][nn - 1] + v[nn], f[cc][nn - 1]);// 切分，这里是反着分析的，也就是到了计算取第nn个物品求最大价值的时候，
                    // 则一定是先选过了第nn-1个物品来的。从nn-1到nn有两条路径
                    // 1，不取第nn-1个物品而来。
                    // 2，取第nn-1个物品而来。
                    // 递归方程：
                    // f(cc, nn) = Math.max(取第nn-1个物品而来, 不取第nn-1个物品而来) ==> f(cc, nn) = Math.max(f(cc-w[nn], nn-1) + v[nn], f(cc, nn-1))
                } else {
                    f[cc][nn] = f[cc][nn - 1];// 如果第nn个物品要装不下了。则是从不取第nn-1个物品而来的
                }
            }
        }

        System.out.println("重量：" + Arrays.toString(w));
        System.out.println("价值：" + Arrays.toString(v));

        for (int i = 0; i < this.f.length; i++) {
            System.out.printf("容量限制为:%s的最大价值\t", i);
            for (int anInt : this.f[i]) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        System.out.println("可以获利最大值为：" + this.f[c][n]);

        System.out.print("选取的物品编号为：");
        int cap = this.c;
        for (int i = n; i >= 1; i--) {
            if (f[cap][i] != f[cap][i - 1]) {// 这里找的是由于i物品的加入，导致的价值变化，
                cap -= w[i];//如果有变化，则表示选取了i物品，否则就是没有取i物品
                System.out.print(i + "\t");
            }
        }
    }

    public static void main(String[] args) {
        new ZeroOnePirate(20, new int[]{20, 10, 5, 15}, new int[]{4, 3, 2, 1}).result();
    }

}
