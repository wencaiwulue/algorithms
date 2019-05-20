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
public class ArrayBlockCut {

    public static void main(String[] args) {
        List<Integer[]> temp = new ArrayList<>();
        temp.add(new Integer[]{1, 2, 3});
        temp.add(new Integer[]{4, 5, 6, 7});
        temp.add(new Integer[]{8, 9, 10, 11, 12, 12});
        System.out.println(test(2, temp).toString());
    }

    private static List<Integer> test(int n, List<Integer[]> list) {
        int maxLength = 0;
        List<Integer> result = new ArrayList<>();
        for (Integer[] integers : list) {
            maxLength = integers.length > maxLength ? integers.length : maxLength;
        }
        int times = maxLength % n == 0 ? maxLength / n : maxLength / n + 1;
        for (int m = 0; m < times; m++) {
            for (int t = 0; t < list.size(); t++) {
                Integer[] temp = list.get(t);
                for (int j = m * n; j < (m + 1) * n && j < temp.length; j++) {
                    result.add(temp[j]);
                }
            }
        }

        return result;
    }

}
