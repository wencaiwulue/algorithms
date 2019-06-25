package collections;

import clone.DeepCloneAndShadowCloneTest;

/**
 * @author fengcaiwen
 * @since 6/25/2019
 */
public class ArrayTest {
    public static void main(String[] args) {
        DeepCloneAndShadowCloneTest.User user1 = new DeepCloneAndShadowCloneTest.User(1, "zhangsan");
        DeepCloneAndShadowCloneTest.User user2 = new DeepCloneAndShadowCloneTest.User(2, "lisi");
        DeepCloneAndShadowCloneTest.User[] users = new DeepCloneAndShadowCloneTest.User[2];
        users[0] = user1;
        users[1] = user2;

        users[0].setName("wangwu");
        System.out.println(users[0].getName());
    }
}
