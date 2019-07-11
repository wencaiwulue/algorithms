package collections;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author fengcaiwen
 * @since 7/11/2019
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> a = Lists.newArrayList(1, 2, 3, 4);
        int middle = BigDecimal.valueOf(a.size()).divide(BigDecimal.valueOf(2), RoundingMode.DOWN).intValue();
        int c = BigDecimal.valueOf(a.size()).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).intValue();
        System.out.println(a.subList(0, middle));
        System.out.println(a.get(middle));
        System.out.println(a.subList(middle + 1, a.size()));

//        System.out.println(a.subList(0, c - 1));
//        System.out.println(a.get(c - 1));
//        System.out.println(a.subList(c, a.size()));


    }
}
