package operator;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 6/14/2019
 */
@SuppressWarnings("all")
public class XorTest {
    public static void main(String[] args) {
        System.out.println(7 ^ 7 ^ 7 ^ 5 ^ 5 ^ 3 ^ 7 ^3^1^1);
        System.out.println(7 | 5);
        System.out.println(7 & 5);
        System.out.println(~-9);
        System.out.println(7 >> 4);
        System.out.println(7 << 2);
        System.out.println(7 >>> 4);


        System.out.println(10 ^ 12 ^ 12 & 10 ^ 60 ^ 9);
        System.out.println(15 ^ 10);
        System.out.println(15 ^ 8);
        int[] integers = new int[]{3, 4, 3, 4, 5, 5,7, 8};
        System.out.println();
        System.out.println(number(integers));

        System.out.println(Math.pow(2, 3));
        System.out.println(5 ^ 6);

        String s = "%E7%99%BB%E9%99%86%E5%A4%B1%E8%B4%A5%EF%BC%8C%E5%B7%B2%E8%B6%85%E8%BF%87%E5%9B%BE%E4%B9%A6%E9%A6%86%E9%99%90%E5%AE%9A%E7%9A%84%E6%9C%80%E5%A4%A7%E8%AE%BE%E5%A4%87%E6%95%B0";
        String decode = URLDecoder.decode(s, Charset.forName("utf8"));
        System.out.println(decode);

    }

    public static int number(int[] source) {
        int a = 1;
        for (int i : source) {
            a = a ^ i;
        }
        a = a ^ 1;


        int point = 0;
        List<Integer> passible = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            point = (int) Math.pow(2, i);
            int one = a + point;
            for (int i1 : source) {
                if ((i1 ^ one) == 0) {
                    passible.add(one);
                }
            }
        }
        for (Integer integer : passible) {
            for (int i : source) {
                if (((a ^ integer) ^ i) == 0) {
                    System.out.println("one: " + integer);
                    System.out.println("other: " + (a ^ integer));
                }
            }
        }


        return a;
    }
}
