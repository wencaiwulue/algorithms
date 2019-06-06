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
    }

}
