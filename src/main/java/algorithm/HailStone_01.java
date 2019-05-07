package algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 4/25/2019
 * <p>
 * The Hailstone sequence of numbers can be generated from a starting positive integer,   n   by:
 * <p>
 * If   n   is     1     then the sequence ends.
 * If   n   is   even then the next   n   of the sequence   = n/2
 * If   n   is   odd   then the next   n   of the sequence   = (3 * n) + 1
 * <p>
 * https://rosettacode.org/wiki/Hailstone_sequence
 */
public class HailStone_01 {
    public static void main(String[] args) {
        int stone = 60;
        System.out.println(hailStone(stone));
        hailStone0(stone);
    }

    private static int hailStone(int value) {
        int length = 0;
        while (value != 1) {
            value = value % 2 == 0 ? value / 2 : value * 3 + 1;
            length++;
        }
        return length;
    }

    private static void hailStone0(int value) {
        int length = 0;
        List<Integer> integerList = new LinkedList<>();
        integerList.add(value);
        while (value > 1) {
            value = value % 2 == 0 ? value / 2 : value * 3 + 1;
            length++;
            integerList.add(value);
        }
        System.out.println(length);
        System.out.println(integerList);
    }
}
