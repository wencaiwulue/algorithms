package question;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.BitSet;

/**
 * @author fengcaiwen
 * @since 4/19/2019
 * <p>
 * 第1步：确定需要筛选素数的范围，确定范围的最大值，比如是120。
 * 第2步：根号120的结果为10.95，所以只需要利用11以内所有素数的倍数来剔除120以内的数字，剩下的就是素数。首先剔除以2为倍数的数字，11以内剔除掉4，6，8，10这几个数字，同时剔除掉120以内所有以2为倍数的数字。
 * 第3步：最小的未被剔除的数字为3，剔除以3为倍数的数字，11以内剔除9这个数字，同时剔除掉120以内所有以3为倍数的数字。
 * 第4步：最小的未被剔除的数字为5，剔除以5为倍数的数字，11以内不需要剔除数字，同时剔除掉120以内所有以5为倍数的数字。……如此类推，可以将120以内的所有素数完全找到。
 */
public class PrimeNumber {


    public static void main(String[] args) {
        int a = 100;
        calculate(a);
    }

    protected static BitSet calculate(int range) {
        int a = BigDecimal.valueOf(Math.sqrt(range)).setScale(0, RoundingMode.FLOOR).intValue();
        BitSet baseSet = new BitSet(range);
        for (int i = 2; i < range; i++)
            baseSet.set(i, true);


        BitSet sqrtSet = new BitSet(a);
        for (int i = 2; i < a; i++)
            sqrtSet.set(i, true);


        for (int i = 2; i < a; i++)
            if (sqrtSet.get(i))
                for (int j = i + 1; j < range; j++)
                    if (j % i == 0) {
                        baseSet.clear(j);
                        sqrtSet.clear(j);
                    }

        for (int i = 0; i < range; i++) {
            if (baseSet.get(i)) {
//                System.out.println(i);
            }
        }
        return baseSet;
    }
}
