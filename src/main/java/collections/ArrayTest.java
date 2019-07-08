package collections;

import clone.DeepCloneAndShadowCloneTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        map.put("check196", list);

        List<DeepCloneAndShadowCloneTest.User> userList = new ArrayList<>();

        final List<DeepCloneAndShadowCloneTest.User> test = map.get("check196");
        test.get(0).setName("haha");

        System.out.println(map.get("check196").get(0).getName());


        int a  =1;
        System.out.println(a++);


    }
}
