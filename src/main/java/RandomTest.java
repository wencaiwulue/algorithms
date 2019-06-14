import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.Random;


public class RandomTest {

    public static void main(String[] args) {
        int[][] b = {{11, 22, 33, 44}, {1, 2, 3, 4}};

        int a1 = 0, a2 = 0, a3 = 0, a4 = 0;
        for (int i = 0; i < 100; i++) {
            int id = getWexinIdByWeight(b);
            if (id == 11) {
                a1++;
            } else if (id == 22) {
                a2++;
            } else if (id == 33) {
                a3++;
            } else if (id == 44) {
                a4++;
            }
        }
        System.out.printf("weight: %d  %d  %d  %d \n", b[1][0], b[1][1], b[1][2], b[1][3]);
        System.out.printf("times:  %s %s %s %s \ntotal times: %s", a1, a2, a3, a4, a1 + a2 + a3 + a4);

    }

    @SuppressWarnings("all")
    public static int getWexinIdByWeight(int[][] source) {
        int totalWeight = 0;
        int length = source[0].length;
        RangeMap<Integer, Integer> map = TreeRangeMap.create();
        int from = 0, step = 0;
        for (int i = 0; i < length; i++) {
            step = source[1][i];
            totalWeight += step;
            map.put(Range.closedOpen(from, from + step), source[0][i]);
            from += step;
        }
        Random random = new Random();
        return map.get(random.nextInt(totalWeight));

    }
}
