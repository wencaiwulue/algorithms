package question;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数n和多行数组，整数代表从每行数组中取出n个元素，
 * 如：
 * 2
 * 1，2，3
 * 4，5，6,7
 * <p>
 * 得到的新数组为： 1，2，4，5，3，6，7
 */
@SuppressWarnings("all")
public class ArrayBlockCut {

    public static void main(String[] args) {
        List<int[]> temp = new ArrayList<>();
        temp.add(new int[]{1, 2, 3});
        temp.add(new int[]{4, 5, 6, 7});
        temp.add(new int[]{8, 9, 10, 11, 12, 12});
        temp.add(new int[]{13, 14, 15, 16, 17, 18, 19, 20});
        System.out.println(test(3, temp).toString());
    }

    private static List<Integer> test(int n, List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        int max = 0;
        for (int[] ints : list) {
            max = ints.length > max ? ints.length : max;
        }
        int times = max % n == 0 ? max / n : max / n + 1;
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < list.size(); j++) {
                int[] temp = list.get(j);
                for (int k = i * n; k < (i + 1) * n && k < temp.length; k++) {
                    result.add(temp[k]);
                }
            }
        }

        return result;
    }

}
