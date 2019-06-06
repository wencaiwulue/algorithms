import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fengcaiwen
 * @since 6/3/2019
 */
public class LambdaTest {
    public static void main(String[] args) {
        Collection<Integer> list = Stream.of(1, 3, 2, 5, 4, 3, 4)
                .collect(Collectors.collectingAndThen(Collectors.toMap(e -> e, e -> e, (o1, o2) -> o1), e -> new ArrayList<>(e.keySet())));
        System.out.println(list);

        int[] a = new int[]{3, 4, 5, 6, 7, 8, 1, 2};
        int[] ints = test(a);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map.get(null));
    }

    private static int[] test(int[] a) {

        int[] b = new int[1024];
        for (int i : a) {
            b[i] = i;
        }

        int j = 0;
        for (int i : b) {
            if (i != 0) {
                a[j++] = i;
            }
        }

        return a;
    }
}
