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
        iterator.forEachRemaining(System.out::println);
//        for (Integer integer : stack) {
//            System.out.println(integer);
//        }

        Stack<Integer>[] stacks = new Stack[10];
        for (int i = 0; i < 10; i++) {
            stacks[i] = new Stack<Integer>();
        }

        Integer peek = stacks[0].peek();
        System.out.println();

        List<Integer> list  = new ArrayList<>();
        while (!stack.empty()){
            list.add(stack.pop());
        }
        System.out.println(list);
    }
}
