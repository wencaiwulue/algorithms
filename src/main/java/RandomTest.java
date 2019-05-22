import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.Random;


public class RandomTest {

    public static void main(String[] args) {
        int[][] b = {{11, 22, 33, 44}, {1, 2, 3, 4}};

        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        for (int i = 0; i < 100; i++) {
            int id = getWexinIdByWeight(b);
            if (id == 11) {
                a1++;
            } else if (id == 22) {
                a2++;
            } else if (id == 33) {
                a3++;
            } else {
                a4++;
            }
        }
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);

    }

    @SuppressWarnings("all")
    public static int getWexinIdByWeight(int[][] source) {
        int totalWeight = 0;
        int length = source[0].length;
        for (int i = 0; i < length; i++) {
            totalWeight += source[1][i];
        }
        // 1，构建一个key为range，value为index的map
        RangeMap<Integer, Integer> map = TreeRangeMap.create();
        int from = 0, step = 0;
        for (int i = 0; i < length; i++) {
            step = source[1][i];
            map.put(Range.closedOpen(from, from + step), source[0][i]);
            from += step;
        }
        Random random = new Random();
        return map.get(random.nextInt(totalWeight));

    }
}
