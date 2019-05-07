//package Optional;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * @author fengcaiwen
// * @since 4/25/2019
// */
//public class MapReduceTest {
//    public static void main(String[] args) {
//        List<String> collect = Stream.of("1,2", "3,4", "5,6").flatMap(e -> Stream.of(e.split(","))).reduce((e1, e2) -> {
//            return e1 + e2;
//        }).stream().collect(Collectors.toList());
//        System.out.println(collect);
//
//        Map<Object, Object> collect0 = Stream.of("1,2", "3,4", "5,6").flatMap(e -> Stream.of(e)).collect(Collectors.toMap(e->e, e->e));
//        System.out.println(collect0);
//    }
//}
