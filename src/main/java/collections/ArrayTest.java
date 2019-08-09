package collections;

import clone.DeepCloneAndShadowCloneTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author fengcaiwen
 * @since 6/25/2019
 */
public class ArrayTest {
    public static void main(String[] args) {
        DeepCloneAndShadowCloneTest.User user1 = new DeepCloneAndShadowCloneTest.User(1, "zhangsan");
        DeepCloneAndShadowCloneTest.User user2 = new DeepCloneAndShadowCloneTest.User(2, "lisi");
        List<DeepCloneAndShadowCloneTest.User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

//        DeepCloneAndShadowCloneTest.User[] users = new DeepCloneAndShadowCloneTest.User[2];
//        users[0] = user1;
//        users[1] = user2;

//        users[0].setName("wangwu");
//        System.out.println(users[0].getName());

        Map<String, List<DeepCloneAndShadowCloneTest.User>> map = new HashMap<>();
        map.put("test", list);

        List<DeepCloneAndShadowCloneTest.User> userList = new ArrayList<>();

        final List<DeepCloneAndShadowCloneTest.User> test = map.get("test");
        test.get(0).setName("haha");

        System.out.println(map.get("test").get(0).getName());


        int a = 1;
        System.out.println(a++);

        int[] b = new int[]{1, 2, 3, 4, 5, 3, 32};
        List<Integer> collect = Arrays.stream(b).sorted().distinct().boxed().collect(Collectors.toList());
        List<Integer> integers = IntStream.range(1, 1000000).boxed().collect(Collectors.toList());


    }
}
