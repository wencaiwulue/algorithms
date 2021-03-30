package algorithm.leecode;

/**
 * @author fengcaiwen
 * @since 8/5/2019
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] ints = new int[]{7, 5, 2, 4, 6, 1, 3};
        System.out.println(maxProfit(ints));
    }

    private static int maxProfit(int[] prices) {
        int m = 0;
        int p = 0;
        int l = prices.length - 1;
        for (int i = l; i >= 0; i--) {
            m = Math.max(prices[i], m);
            int i1 = m - prices[i];
            p = Math.max(p, i1);
        }
        return p;
    }
}
