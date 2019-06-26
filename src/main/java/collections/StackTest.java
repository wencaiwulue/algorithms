package collections;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengcaiwen
 * @since 6/15/2019 16:03
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        Iterator<Integer> iterator = stack.iterator();
        iterator.forEachRemaining(e-> System.out.println(e));
//        for (Integer integer : stack) {
//            System.out.println(integer);
//        }

        List<Integer> list  = new ArrayList<>();
        while (!stack.empty()){
            list.add(stack.pop());
        }
        System.out.println(list);
    }
}
